package com.github.mrmks.sapi_extends.compound.mechanic;

import com.github.mrmks.sapi_extends.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.serverct.ersha.jd.AttributeAPI;
import org.serverct.ersha.jd.attribute.AttributeData;
import org.serverct.ersha.jd.attribute.enums.AttributeName;

import java.util.List;

public class APDamage extends CustomMechanic {

    private static final String TYPE       = "type";
    private static final String MAX_DAMAGE = "max_value";
    private static final String MIN_DAMAGE = "min_value";
    private static final String DAMAGE     = "value";
    private static final String TRUE       = "true";
    private static final String CLASSIFIER = "classifier";

    @Override
    public String getKey() {
        return "AP_Damage";
    }

    @Override
    public String getDescription() {
        return "Used for AttributePoint plugin, give the final damage to target";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown(TYPE, "Type", "[type]", ImmutableList.of(
                        "Damage","Multiplier","Percent","Percent Missing","Percent Left"
                )),
                EditorOption.number(DAMAGE, "Number", "[value]When type isn't Damage, the value means percentage.ig. 10 means 10%",3, 1),
                EditorOption.number(MAX_DAMAGE, "Max Damage","["+MAX_DAMAGE+"]这个属性会在百分比伤害时生效,在AP的特殊计算公式下,最终的伤害有可能大于该值,设置为-1时忽略", 0, 0),
                EditorOption.number(MIN_DAMAGE, "Min Damage", "["+MIN_DAMAGE+"]这个属性在类型为非Damage时生效,设置为-1时忽略", 0 ,0),
                EditorOption.dropdown(TRUE, "True Damage", "[true]", ImmutableList.of("false", "true")),
                EditorOption.text(CLASSIFIER, "Classifier", "[classifier]", "default")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (livingEntity instanceof Player) {
            DamageType type = getDamageType(settings.getString(TYPE, "damage").toLowerCase());
            String classification = settings.getString(CLASSIFIER, "default");
            boolean trueDmg = settings.getBool(TRUE, false);

            Player player = (Player) livingEntity;
            double n = parseValues(livingEntity, DAMAGE, i, 1);
            double max = parseValues(livingEntity, MAX_DAMAGE, i, 1);
            double min = parseValues(livingEntity, MIN_DAMAGE, i, 1);

            double maxDmg = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
            double apDmg = parseAPRange(AttributeName.DAMAGE, player,0);
            double charge = parseAPSingle(AttributeName.CHARGE_ADDITION, player,0) / 100;

            for (LivingEntity target : list) {
                if (target.isDead()) {
                    continue;
                }

                double sDmg = apDmg;
                if (target instanceof Player) {
                    sDmg += parseAPRange(AttributeName.PVP_DAMAGE, player, 0);
                } else {
                    sDmg += parseAPRange(AttributeName.PVE_DAMAGE, player, 0);
                }

                double m = n;
                switch (type) {
                    case MULTIPLIER:
                        m *= sDmg / 100;
                        break;
                    case PERCENT:
                        m *= getMaxHealth(target) / 100;
                        break;
                    case PERCENT_LEFT:
                        m *= (getMaxHealth(target) - target.getHealth()) / 100;
                        break;
                    case PERCENT_MISSION:
                        m *= target.getHealth() / 100;
                        break;
                }

                if (trueDmg) {
                    skill.trueDamage(target, m, livingEntity);
                } else {
                    skill.damage(target, getFixedDamage(trimToSize(type, m, min, max), charge, sDmg, maxDmg), livingEntity, classification);
                }
            }
            return true;
        }
        return false;
    }

    private double getMaxHealth(LivingEntity entity) {
        return entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }

    private double parseAPRange(AttributeName key, Player player, double def){
        AttributeData data = AttributeAPI.getAttrData(player);
        Number[] nums = data.getAttributeValues(key.toDefaultName());
        if (nums.length == 2) {
            try {
                double a = nums[0].doubleValue();
                double b = nums[1].doubleValue();
                return Math.round(Math.abs(a - b)) + Math.min(a,b);
            } catch (Throwable ignored){}
        }
        return def;
    }

    private double parseAPSingle(AttributeName key, Player player, double def){
        AttributeData data = AttributeAPI.getAttrData(player);
        Number num = data.getAttributeValue(key.toDefaultName());
        try {
            return num.doubleValue();
        } catch (Throwable ignored){}
        return def;
    }

    private double getFixedDamage(double m, double c, double y, double z){
        c = Math.min(Math.max(c,0),1);
        if (m == 0) return 0;
        else {
            if (m - y >= 1 - c){
                return m - y == 0 ? 0.00001 : m - y ;
            } else {
                if (c * y >= m) return 0.00001;
                else {
                    double zc = z * c;
                    double zm = z * m;
                    return (Math.sqrt(zm * 4 + Math.pow(y - zc,2)) - y - zc) / 2;
                }
            }
        }
    }

    private DamageType getDamageType(String type) {
        try {
            return DamageType.valueOf(type.toUpperCase().replaceAll("\\s+", "_"));
        } catch (Throwable tr) {
            return DamageType.DAMAGE;
        }
    }

    private double trimToSize(DamageType type, double re, double min, double max){
        double v = min;
        min = Math.min(v, max);
        max = Math.max(v, max);
        v = min < 0 ? re : Math.max(re, min);
        switch (type) {
            case MULTIPLIER:
                return v;
            case PERCENT:
            case PERCENT_LEFT:
            case PERCENT_MISSION:
                re = v;
                re = max < 0 ? re : Math.min(re, max);
                return re;
            case DAMAGE:
            default:
                return re;
        }
    }

    private enum DamageType {
        DAMAGE,
        MULTIPLIER,
        PERCENT,
        PERCENT_LEFT,
        PERCENT_MISSION
    }
}

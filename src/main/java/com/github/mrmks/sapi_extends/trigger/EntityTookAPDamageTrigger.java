package com.github.mrmks.sapi_extends.trigger;

import com.github.mrmks.sapi_extends.bridge.BridgeDamageEntityEvent;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.api.Settings;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomTrigger;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class EntityTookAPDamageTrigger implements CustomTrigger<BridgeDamageEntityEvent> {
    @Override
    public String getKey() {
        return "TOOK_AP_DAMAGE";
    }

    @Override
    public String getDisplayName() {
        return "Took AP Damage";
    }

    @Override
    public ComponentType getType() {
        return ComponentType.TRIGGER;
    }

    @Override
    public String getDescription() {
        return "Applies skill effects when a player deals damage made by AttributePlus; The damage store under key api-dealt";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown(
                        "target",
                        "Target Caster",
                        "True makes children target the caster. False makes children target the entity.",
                        ImmutableList.of("True", "False")),
                EditorOption.number(
                        "dmg-min",
                        "Min Damage",
                        "The minimum damage that needs to be dealt",
                        0,
                        0),
                EditorOption.number(
                        "dmg-max",
                        "Max Damage",
                        "The maximum damage that can be dealt",
                        999,
                        0)
        );
    }

    @Override
    public Class<BridgeDamageEntityEvent> getEvent() {
        return BridgeDamageEntityEvent.class;
    }

    @Override
    public boolean shouldTrigger(BridgeDamageEntityEvent bridgeDamageEvent, int i, Settings settings) {
        double min = settings.getDouble("dmg-min", 0);
        double max = settings.getDouble("dmg-max", 999);
        double damage = bridgeDamageEvent.getEvent().getFinalDamage();

        return damage >= min && damage <= max;
    }

    @Override
    public void setValues(BridgeDamageEntityEvent bridgeDamageEvent, Map<String, Object> map) {
        map.put("api-dealt", bridgeDamageEvent.getEvent().getFinalDamage());
    }

    @Override
    public LivingEntity getCaster(BridgeDamageEntityEvent bridgeDamageEvent) {
        Entity entity = bridgeDamageEvent.getEvent().getEntity();
        if (entity instanceof LivingEntity) return (LivingEntity) entity;
        else return null;
    }

    @Override
    public LivingEntity getTarget(BridgeDamageEntityEvent event, Settings settings) {
        boolean isUsingTarget = settings.getString("target", "true").equalsIgnoreCase("false");
        Entity entity = isUsingTarget ? event.getEvent().getDamager() : event.getEvent().getEntity();
        return entity instanceof LivingEntity ? (LivingEntity) entity : null;
    }
}

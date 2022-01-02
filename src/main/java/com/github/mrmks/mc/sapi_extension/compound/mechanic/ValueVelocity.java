package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

public class ValueVelocity extends CustomMechanic {
    @Override
    public String getKey() {
        return "value velocity";
    }

    @Override
    public String getDescription() {
        return "Value velocity of target, values under suffixed-key _x, _y, _z, _l";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text("key", "The perfix of the key", "key"),
                EditorOptionHelper.dropdown("norm", "Normalize", "Should normalize the vector when length isn't 0", ImmutableList.of("false", "true"))
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (settings.has("key") && list.size() > 0) {
            LivingEntity le = list.get(0);
            Vector vec = le.getVelocity();
            Map<String, Object> map = DynamicSkill.getCastData(livingEntity);
            String key = settings.getString("key", "Key");
            if (vec.length() > Vector.getEpsilon()) {
                vec = settings.getBool("norm", false) ? vec.normalize() : vec;
                map.put(key.concat("_x"), vec.getX());
                map.put(key.concat("_y"), vec.getY());
                map.put(key.concat("_z"), vec.getZ());
                map.put(key.concat("_l"), vec.length());
            } else {
                Double d = 0d;
                map.put(key.concat("_x"), d);
                map.put(key.concat("_y"), d);
                map.put(key.concat("_z"), d);
                map.put(key.concat("_l"), d);
            }
            return true;
        }
        return false;
    }
}

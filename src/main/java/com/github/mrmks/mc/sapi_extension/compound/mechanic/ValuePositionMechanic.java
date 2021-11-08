package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValuePositionMechanic extends CustomMechanic {

    public static final String KEY = "key";

    @Override
    public String getKey() {
        return "value position";
    }

    @Override
    public String getDisplayName() {
        return "Value Position";
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Value x,y,z,yaw,pitch,world params of a location";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text(KEY, "The value will be stored under 'key_x, key_y, key_z and so on'", "key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (settings.has(KEY) && list.size() > 0) {
            String key = settings.getString(KEY).concat("_");
            Map<String, Object> map = DynamicSkill.getCastData(livingEntity);
            Location loc = list.get(0).getLocation();
            map.put(key.concat("x"), loc.getX());
            map.put(key.concat("y"), loc.getY());
            map.put(key.concat("z"), loc.getZ());
            map.put(key.concat("yaw"), loc.getYaw());
            map.put(key.concat("pitch"), loc.getPitch());
            map.put(key.concat("world"), loc.getWorld().getName());
            return true;
        } else {
            return false;
        }
    }
}

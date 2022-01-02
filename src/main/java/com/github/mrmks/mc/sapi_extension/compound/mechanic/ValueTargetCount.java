package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValueTargetCount extends CustomMechanic {
    @Override
    public String getKey() {
        return "value target count";
    }

    @Override
    public String getDescription() {
        return "Value the count of target";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(EditorOptionHelper.text("key", "Where to store the number", "key"));
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (settings.has("key")) {
            String key = settings.getString("key", "key");
            DynamicSkill.getCastData(livingEntity).put(key, (double) list.size());
            return true;
        }
        return false;
    }
}

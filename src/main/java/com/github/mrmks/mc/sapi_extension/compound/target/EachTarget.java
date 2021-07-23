package com.github.mrmks.mc.sapi_extension.compound.target;

import com.github.mrmks.mc.sapi_extension.compound.CustomTarget;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.Collections;
import java.util.List;

public class EachTarget extends CustomTarget {
    @Override
    public String getKey() {
        return "each";
    }

    @Override
    public String getDisplayName() {
        return "Each";
    }

    @Override
    public String getDescription() {
        return "Apply child components to each target";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.dropdown("stop", "Stop", "[stop] Stop on failed to execute?", ImmutableList.of("False", "True"))
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        boolean flag = true;
        boolean stop = settings.getString("stop", "False").equalsIgnoreCase("True");

        for (LivingEntity le : list) {
            flag &= executeChildren(livingEntity, i, Collections.singletonList(le));
            if (stop && !flag) break;
        }
        return !stop || flag;
    }
}

package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ReverseTargetMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "reverse target";
    }

    @Override
    public String getDescription() {
        return "(Experiment)Reverse the caster and the target in children components";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of();
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        return !list.isEmpty() && executeChildren(list.get(0), i, Lists.newArrayList(livingEntity));
    }
}

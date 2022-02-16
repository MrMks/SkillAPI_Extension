package com.github.mrmks.mc.sapi_extension.compound.condition.fixer;

import org.bukkit.entity.LivingEntity;

import java.util.List;

public class NameCondition extends com.sucy.skill.dynamic.condition.NameCondition {
    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        settings.set("str", settings.getString("text", ""));
        return super.execute(livingEntity, i, list);
    }
}

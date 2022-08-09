package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.github.mrmks.mc.sapi_extension.event.ManualTriggerEvent;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ManualTriggerMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "manual trigger";
    }

    @Override
    public String getDisplayName() {
        return "Manual Trigger";
    }

    @Override
    public String getDescription() {
        return "Manual Trigger, There are only one trigger response to this if you set many triggers with same key";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key", "Key", "[key]The key to identity which manual trigger should trigger", "key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = getSettings().getString("key", "key");
        boolean match = false;
        for (LivingEntity t : list) {
            ManualTriggerEvent event = new ManualTriggerEvent(key, livingEntity, t);
            Bukkit.getPluginManager().callEvent(event);
            match = match || event.isMatch();
        }
        return match;
    }
}

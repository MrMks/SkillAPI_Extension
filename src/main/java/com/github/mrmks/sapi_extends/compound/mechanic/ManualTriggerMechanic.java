package com.github.mrmks.sapi_extends.compound.mechanic;

import com.github.mrmks.sapi_extends.compound.CustomMechanic;
import com.github.mrmks.sapi_extends.event.ManualTriggerEvent;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ManualTriggerMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "Manual_Trigger";
    }

    @Override
    public String getDescription() {
        return getDisplayName();
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

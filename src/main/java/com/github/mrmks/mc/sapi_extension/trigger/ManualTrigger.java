package com.github.mrmks.mc.sapi_extension.trigger;

import com.github.mrmks.mc.sapi_extension.event.ManualTriggerEvent;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.api.Settings;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class ManualTrigger extends CustomTrigger<ManualTriggerEvent> {

    public ManualTrigger() {
        super("manual trigger", ManualTriggerEvent.class);
    }

    @Override
    public String getDescription() {
        return "Apply ManualTrigger for each target";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key", "Key", "[key]The key which can trigger this", "key")
        );
    }

    @Override
    public boolean shouldTrigger(ManualTriggerEvent manualTriggerEvent, int i, Settings settings) {
        return manualTriggerEvent.isMatch(settings.getString("key", ""));
    }

    @Override
    public void setValues(ManualTriggerEvent manualTriggerEvent, Map<String, Object> map) {
    }

    @Override
    public LivingEntity getCaster(ManualTriggerEvent manualTriggerEvent) {
        return manualTriggerEvent.getCaster();
    }

    @Override
    public LivingEntity getTarget(ManualTriggerEvent manualTriggerEvent, Settings settings) {
        return manualTriggerEvent.getTarget();
    }
}

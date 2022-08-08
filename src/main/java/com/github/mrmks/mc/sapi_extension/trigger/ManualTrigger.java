package com.github.mrmks.mc.sapi_extension.trigger;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.event.ManualTriggerEvent;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.api.Settings;
import com.sucy.skill.dynamic.DynamicSkill;
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
        return "Apply ManualTrigger for each target. Only one trigger will response to each mechanic call even if many triggers have same key.";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key", "Key", "[key]The key which can trigger this", "key"),
                EditorOptionHelper.dropdown("delay", "Should we pass the key check and let child-components to check it? If you set this to true, you can check it with the key \"api-key\" and make sure to set a value \"api-result\" smaller that 0 if you can't handle this key, or we will mark the event as been responded.", ImmutableList.of("false", "true"))
        );
    }

    @Override
    public boolean shouldTrigger(ManualTriggerEvent manualTriggerEvent, int i, Settings settings) {
        return !manualTriggerEvent.isMatch() && (settings.getBool("delay", false) || manualTriggerEvent.isMatch(settings.getString("key", "")));
    }

    @Override
    public void setValues(ManualTriggerEvent manualTriggerEvent, Map<String, Object> map) {
        map.put("api-key", manualTriggerEvent.getKey());
    }

    @Override
    public LivingEntity getCaster(ManualTriggerEvent manualTriggerEvent) {
        return manualTriggerEvent.getCaster();
    }

    @Override
    public LivingEntity getTarget(ManualTriggerEvent manualTriggerEvent, Settings settings) {
        return manualTriggerEvent.getTarget();
    }

    @Override
    public void postProcess(ManualTriggerEvent event, DynamicSkill skill) {
        Map<String, Object> map = DynamicSkill.getCastData(event.getCaster());
        map.remove("api-key");
        Object obj = map.remove("api-result");
        if (!(obj instanceof Number) || ((Number) obj).doubleValue() >= 0) event.markResponded();
    }
}

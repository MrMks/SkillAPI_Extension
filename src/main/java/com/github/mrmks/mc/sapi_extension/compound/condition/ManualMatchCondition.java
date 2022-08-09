package com.github.mrmks.mc.sapi_extension.compound.condition;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomCondition;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class ManualMatchCondition extends CustomCondition {
    @Override
    protected boolean test(LivingEntity caster, int level, List<LivingEntity> targets) {
        Map<String, Object> map = DynamicSkill.getCastData(caster);
        Object obj0 = map.get("api-key");
        boolean flag = obj0 instanceof String && obj0.equals(settings.getString("key", "key"));
        map.put("api-result", flag ? 1 : -1);
        return flag;
    }

    @Override
    public String getKey() {
        return "manual match";
    }

    @Override
    public String getDescription() {
        return "Test whether the key is match when the delay in Manual Trigger is true";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(EditorOptionHelper.text("key", "The key excepted", "key"));
    }
}

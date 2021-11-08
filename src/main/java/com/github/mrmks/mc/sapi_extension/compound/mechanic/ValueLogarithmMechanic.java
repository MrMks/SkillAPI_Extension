package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class ValueLogarithmMechanic extends CustomMechanic {

    public static final String KEY = "key";
    public static final String BASE = "base";
    public static final String NUM = "num";
    public static final String EXCEPTION = "exception";

    @Override
    public String getKey() {
        return "value logarithm";
    }

    @Override
    public String getDisplayName() {
        return "Value Logarithm";
    }

    @Override
    public String getDescription() {
        return "Get the value of log_a_b";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text(KEY, "The result to store", "key"),
                EditorOptionHelper.number(BASE, "", 1, 0),
                EditorOptionHelper.number(NUM, "", 0, 0),
                EditorOptionHelper.number(EXCEPTION, "The value to use if any exception happens", 0, 0)
        );
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (list.size() > 0 && settings.has(KEY)) {
            String key = settings.getString(KEY, "key");
            double base = parseValues(livingEntity, BASE, i, 1);
            double num = parseValues(livingEntity, NUM, i, 0);
            double exception = parseValues(livingEntity, EXCEPTION, i, 0);
            Map<String, Object> map = DynamicSkill.getCastData(livingEntity);
            base = Math.log(base);
            num = Math.log(num);
            try {
                map.put(key, num / base);
            } catch (Throwable tr) {
                map.put(key, exception);
            }
            return true;
        } else return false;
    }
}

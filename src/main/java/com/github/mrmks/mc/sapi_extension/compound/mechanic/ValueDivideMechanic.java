package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValueDivideMechanic extends CustomMechanic {

    private static final String NUMERATOR = "numerator";
    private static final String DENOMINATOR = "denominator";
    private static final String KEY = "key";
    private static final String EXCEPTION = "exception";

    @Override
    public String getKey() {
        return "value divide";
    }

    @Override
    public String getDisplayName() {
        return "Value Divide";
    }

    @Override
    public String getDescription() {
        return "Divide numerator by denominator";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text(KEY, "The value to store", "key"),
                EditorOptionHelper.number(NUMERATOR, "", 0, 0),
                EditorOptionHelper.number(DENOMINATOR, "", 1, 0),
                EditorOptionHelper.number(EXCEPTION, "The number to use if any exception happens, if this isn't an number, then it will be 0", 0, 0)
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
            double numerator = parseValues(livingEntity, NUMERATOR, i, 0);
            double denominator = parseValues(livingEntity, DENOMINATOR, i, 1);
            double r;
            try {
                r = numerator / denominator;
            } catch (Throwable tr) {
                r = 0;
            }
            DynamicSkill.getCastData(livingEntity).put(key, r);
            return true;
        } else return false;
    }
}

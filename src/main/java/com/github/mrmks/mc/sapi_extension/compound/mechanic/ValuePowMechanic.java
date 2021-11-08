package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValuePowMechanic extends CustomMechanic {

    public static final String KEY = "key";
    public static final String BASE = "base";
    public static final String EXPONENT = "exponent";

    @Override
    public String getKey() {
        return "value pow";
    }

    @Override
    public String getDisplayName() {
        return "Value Pow";
    }

    @Override
    public String getDescription() {
        return "Get the value of a^b";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text(KEY, "The result to store", "key"),
                EditorOptionHelper.number(BASE, "", 1, 0),
                EditorOptionHelper.number(EXPONENT, "", 0, 0)
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
            double exponent = parseValues(livingEntity, EXPONENT, i, 0);
            DynamicSkill.getCastData(livingEntity).put(key, Math.pow(base, exponent));
            return true;
        } else return false;
    }
}

package com.github.mrmks.sapi_extends.compound.mechanic;

import com.github.mrmks.sapi_extends.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValueSumMechanic extends CustomMechanic {

    @Override
    public String getKey() {
        return "VALUE_SUM";
    }

    @Override
    public String getDisplayName() {
        return "Value Sum";
    }

    @Override
    public String getDescription() {
        return "sum the given values";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("key", "Key", "[key]The key to store the value", "value"),
                EditorOption.dropdown("invert", "Invert", "[invert]Whether or not invert the values, true means subtract, false means add", ImmutableList.of("false", "true")),
                EditorOption.list("list", "List", "[list]The list of values", ImmutableList.of())
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        int m = settings.getString("invert", "false").equalsIgnoreCase("true") ? -1 : 1;
        String key = settings.getString("key", "value");
        double base = getNum(livingEntity, key, 0);
        List<String> strList = settings.getStringList("list");
        for (String k : strList) base += m * getNum(livingEntity, k, 0);
        settings.set(key, base);
        return true;
    }
}

package com.github.mrmks.mc.sapi_extension.compound.condition;

import com.github.mrmks.mc.sapi_extension.compound.CustomCondition;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValueCompareCondition extends CustomCondition {
    @Override
    public String getKey() {
        return "value compare";
    }

    @Override
    public String getDisplayName() {
        return "Value Compare";
    }

    @Override
    public String getDescription() {
        return "Compare two value with the given expression";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOption.text("value_1", "Value 1", "[value_1]", "value"),
                EditorOption.text("value_2", "Value 2", "[value_2]", "value"),
                EditorOption.dropdown(
                        "expr",
                        "Expression",
                        "[expr]Compare two value with given method;EQ means equal, NE means not equal; GE(LE) means greater(less) than or equal; GT(LT) means greater(less) than",
                        ImmutableList.of("EQ", "NE", "GE", "GT", "LE", "LT", "TEXT_EQ")
                )
        );
    }

    public boolean test(LivingEntity caster, int level, List<LivingEntity> targets) {
        String exp = settings.getString("expr");
        if (!exp.equals("TEXT_EQ")) {
            double v1 = getNum(caster, "value_1", 0);
            double v2 = getNum(caster, "value_2", 0);
            switch (exp) {
                case "EQ":
                    return v1 == v2;
                case "NE":
                    return v1 != v2;
                case "GE":
                    return v1 >= v2;
                case "GT":
                    return v1 > v2;
                case "LE":
                    return v1 <= v2;
                case "LT":
                    return v1 < v2;
            }
        } else {
            String v1 = getSettings().getString("value_1");
            String v2 = getSettings().getString("value_2");
            return filter(caster, caster, v1).equals(filter(caster, caster, v2));
        }
        return false;
    }
}

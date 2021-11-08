package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValueTrigonometricMechanic extends CustomMechanic {

    public static final String KEY = "key";
    public static final String NUM = "num";
    public static final String FUNCTION = "func";

    @Override
    public String getKey() {
        return "value trigonometric";
    }

    @Override
    public String getDisplayName() {
        return "Value Trigonometric";
    }

    @Override
    public String getDescription() {
        return "Get the result of trigonometric function";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text(KEY, "Where the result to store", "key"),
                EditorOptionHelper.dropdown(FUNCTION, "The function to use", ImmutableList.of("sin", "cos", "tan", "asin", "acos", "atan")),
                EditorOptionHelper.number(NUM, "The number to use, in rad if an angle", 0, 0)
        );
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (list.size() > 0 && settings.has(KEY)) {
            String key = settings.getString(KEY);
            String func = settings.getString(FUNCTION, "sin");
            double num = parseValues(livingEntity, NUM, i, 0);
            switch (func) {
                default:
                case "sin": num = Math.sin(num);break;
                case "cos": num = Math.cos(num);break;
                case "tan": num = Math.tan(num);break;
                case "asin": num = Math.asin(num);break;
                case "acos": num = Math.acos(num);break;
                case "atan": num = Math.atan(num);break;
            }
            DynamicSkill.getCastData(livingEntity).put(key, num);
            return true;
        } else return false;
    }
}

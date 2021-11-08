package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ValueClearMechanic extends CustomMechanic {

    public static final String KEY = "key";

    @Override
    public String getKey() {
        return "value clear";
    }

    @Override
    public String getDisplayName() {
        return "Value Clear";
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Clear the value";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text(KEY, "The value to clear", "key")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (settings.has(KEY)) {
            DynamicSkill.getCastData(livingEntity).remove(settings.getString(KEY));
            return true;
        } else {
            return false;
        }
    }
}

package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;

public class TargetValueCopyMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "target value copy";
    }

    @Override
    public String getDescription() {
        return "Copy the value from target to caster, or from caster to target";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.dropdown("to", "defines from who to who", ImmutableList.of("target", "caster")),
                EditorOptionHelper.text("src", "defines the key of the value to copy", "key"),
                EditorOptionHelper.text("tar", "defines the key of the copied value", "key"),
                EditorOptionHelper.dropdown("clean", "Should we remove the value in src?", ImmutableList.of("false", "true"))
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (list.isEmpty()) return false;
        String src = settings.getString("src", "key");
        String tar = settings.getString("tar", "key");
        boolean toTar = "target".equalsIgnoreCase(settings.getString("to", "target"));
        boolean clean = "true".equalsIgnoreCase(settings.getString("clean", "false"));

        HashMap<String, Object> srcMap = DynamicSkill.getCastData(toTar ? livingEntity : list.get(0));
        DynamicSkill.getCastData(toTar ? list.get(0) : livingEntity).put(tar, clean ? srcMap.remove(src) : srcMap.get(src));

        return true;
    }
}

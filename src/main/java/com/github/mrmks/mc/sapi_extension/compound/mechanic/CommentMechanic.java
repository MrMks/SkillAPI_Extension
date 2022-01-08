package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class CommentMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "comment";
    }

    @Override
    public String getDescription() {
        return "Comment a group of components";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text("cmt", "Comment","Your comment", ""),
                EditorOptionHelper.dropdown("skip", "Should we skip child components?", ImmutableList.of("false", "true"))
        );
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        return settings.getBool("skip", false) || executeChildren(livingEntity, i, list);
    }
}

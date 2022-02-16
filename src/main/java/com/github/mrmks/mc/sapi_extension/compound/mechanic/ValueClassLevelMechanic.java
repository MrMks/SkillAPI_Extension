package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.api.player.PlayerClass;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class ValueClassLevelMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "value class level";
    }

    @Override
    public String getDescription() {
        return "Value the class level of the casting skill of the caster";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text("key", "The unique key to store the value under.", "level")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String key = settings.getString("key", "level");
        PlayerSkill skill = getSkillData(livingEntity);
        if (skill == null) return false;
        PlayerClass profession = skill.getPlayerClass();
        if (profession == null) return false;
        Double level = (double) profession.getLevel();
        Map<String, Object> map = DynamicSkill.getCastData(livingEntity);
        map.put(key, level);

        return true;
    }
}

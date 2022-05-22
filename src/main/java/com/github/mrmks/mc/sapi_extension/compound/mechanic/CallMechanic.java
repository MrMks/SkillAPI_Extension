package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class CallMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "call";
    }

    @Override
    public String getDescription() {
        return "Call another skill, via the cast trigger.";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text("skill","The skill to cast", "")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        String skill = settings.getString("skill", "");
        if (skill.isEmpty()) return false;
        PlayerData pd = getSkillData(livingEntity).getPlayerData();
        PlayerSkill ps = pd.getSkill(skill);

        return pd.cast(ps);
        
    }
}

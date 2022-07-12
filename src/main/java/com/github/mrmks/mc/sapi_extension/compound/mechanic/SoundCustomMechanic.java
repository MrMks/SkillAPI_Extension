package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import com.sucy.skill.log.Logger;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SoundCustomMechanic extends CustomMechanic {
    @Override
    public String getKey() {
        return "sound custom";
    }

    @Override
    public String getDescription() {
        return "Play custom sound indicated by String, at targets' location";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.text("sound", "The sound resource to play", "minecraft:block.anvil.land"),
                EditorOptionHelper.dropdown("category", "", Arrays.stream(SoundCategory.values()).map(SoundCategory::name).collect(Collectors.toList())),
                EditorOptionHelper.number("volume", "Max to 1, float numbers are available", 1, 0),
                EditorOptionHelper.number("pitch", "range from 0.5 to 2.0", 1, 0)
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (!list.isEmpty()) {
            String sound = settings.getString("sound");
            String strCate = settings.getString("category");

            SoundCategory category;
            try {
                category = SoundCategory.valueOf(strCate);
            } catch (IllegalArgumentException e) {
                category = SoundCategory.MASTER;
                Logger.invalid(strCate + " used in skill " + skill.getName() + " is not a valid sound category, setting to MASTER");
            }

            float vo = (float) parseValues(livingEntity, "volume", i, 1.0D);
            float pi = (float) parseValues(livingEntity, "pitch", i, 1.0D);

            vo = Math.max(0.0F, vo);
            pi = Math.min(2.0F, Math.max(0.5F, pi));

            for (LivingEntity tar : list) {
                tar.getWorld().playSound(tar.getLocation(), sound, category, vo, pi);
            }
            return true;
        }
        return false;
    }
}

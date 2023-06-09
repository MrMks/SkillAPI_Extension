package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class EffekActionMechanic extends CustomMechanic {

    @Override
    public String getKey() {
        return "effek action";
    }

    @Override
    public String getDescription() {
        return "Effek commands wrapper.";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.dropdown("command", "", ImmutableList.of("play", "stop", "clear", "trigger")),
                EditorOptionHelper.text("effect", "", ""),
                EditorOptionHelper.text("emitter", "emitter", ""),
                EditorOptionHelper.text("trigger", "only work with command trigger", "0"),
                EditorOptionHelper.text("options", "", "")
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {

        try {
            Class.forName("com.github.mrmks.mc.efscraft.common.Constants");
        } catch (ClassNotFoundException e) {
            return true;
        }

        String command = settings.getString("command");
        String effect = settings.getString("effect");
        String emitter = settings.getString("emitter");
        String trigger = settings.getString("trigger");
        String options = settings.getString("options");

        StringBuilder leftBd = new StringBuilder().append("effek").append(" ").append(command).append(" ");
        if ("trigger".equals(command)) leftBd.append(trigger).append(" ");
        leftBd.append(effect).append(" ").append(emitter).append(" on ");
        String left = leftBd.toString();

        String right = " " + options;

        for (LivingEntity entity : list) {
            String full = left + entity.getUniqueId() + right;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), full);
        }

        return true;
    }
}

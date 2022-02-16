package com.github.mrmks.mc.sapi_extension.compound.mechanic.fixer;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.dynamic.DynamicSkill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class ValueManaMechanic extends com.sucy.skill.dynamic.mechanic.ValueManaMechanic {
    private static final String KEY = "key";
    private static final String TYPE = "type";
    @Override
    public boolean execute(LivingEntity caster, int level, List<LivingEntity> targets) {
        if (!(targets.get(0) instanceof Player)) return false;

        final PlayerData player = SkillAPI.getPlayerData((Player)targets.get(0));
        final String key = settings.getString(KEY);
        final String type = settings.getString(TYPE, "current").toLowerCase();
        final HashMap<String, Object> data = DynamicSkill.getCastData(caster);

        switch (type) {
            case "max":
                data.put(key, player.getMaxMana());
                break;
            case "percent":
                data.put(key, player.getMana() / player.getMaxMana());
                break;
            case "missing":
                data.put(key, player.getMaxMana() - player.getMana());
                break;
            default: // current
                data.put(key, player.getMana());
                break;
        }
        return true;
    }
}

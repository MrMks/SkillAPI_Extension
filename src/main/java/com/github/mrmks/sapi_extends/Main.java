package com.github.mrmks.sapi_extends;

import com.github.mrmks.sapi_extends.bridge.BridgeListener;
import com.github.mrmks.sapi_extends.compound.condition.ValueCompareCondition;
import com.github.mrmks.sapi_extends.compound.mechanic.ManualTriggerMechanic;
import com.github.mrmks.sapi_extends.trigger.EntityTookAPDamageTrigger;
import com.github.mrmks.sapi_extends.trigger.ManualTrigger;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;
import com.sucy.skill.dynamic.ComponentRegistry;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.trigger.Trigger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements SkillPlugin {
    @Override
    public void onEnable() {
        super.onLoad();
        Bukkit.getPluginManager().registerEvents(new BridgeListener(), this);
    }

    @Override
    public void registerSkills(SkillAPI skillAPI) {
        //skillAPI.addSkill(new NonSkill());
    }

    @Override
    public void registerClasses(SkillAPI skillAPI) {
        //skillAPI.addClass(new NonClass());
    }

    @Override
    public List<CustomEffectComponent> getComponents() {
        return ImmutableList.of(
                new ValueCompareCondition(),
                new ManualTriggerMechanic()
                //new ValueSumMechanic()
        );
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Trigger> getTriggers() {
        List<Trigger> list = new ArrayList<>(ImmutableList.of(
                new EntityTookAPDamageTrigger(),
                new ManualTrigger()
        ));
        list.removeIf(t -> ComponentRegistry.getTrigger(t.getKey()) != null);
        return list;
    }
}

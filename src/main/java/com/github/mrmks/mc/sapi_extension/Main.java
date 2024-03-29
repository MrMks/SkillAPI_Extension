package com.github.mrmks.mc.sapi_extension;

import com.github.mrmks.mc.sapi_extension.compound.condition.*;
import com.github.mrmks.mc.sapi_extension.compound.mechanic.*;
import com.github.mrmks.mc.sapi_extension.compound.target.*;
import com.github.mrmks.mc.sapi_extension.trigger.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;
import com.sucy.skill.dynamic.ComponentRegistry;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;
import com.sucy.skill.dynamic.trigger.Trigger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements SkillPlugin {
    @Override
    public void onEnable() {
        super.onLoad();
        //Bukkit.getPluginManager().registerEvents(new BridgeListener(), this);
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
        //coverComponents();
        return ImmutableList.of(
                new ValueCompareCondition(),        // 双值比较
                new ManualTriggerMechanic(),        // 手动触发器
                new ManualMatchCondition(),         // 延迟的比较方法
                new EachTarget(),                   // 为每个对象应用子组件
                new ValueDivideMechanic(),          // 除法
                new ValueLogarithmMechanic(),       // Log_a_b
                new ValueTrigonometricMechanic(),   // 三角函数
                new ValuePowMechanic(),             // a^b
                new ValuePositionMechanic(),        // x,y,z,yaw,pitch,world
                new ValueClearMechanic(),           // 手动清理记录值
                //new ValueVelocity(),                // 记录运动向量
                new ValueTargetCount(),              // 记录目标数量
                new CommentMechanic(),               // 注释
                //new TargetValueCopyMechanic(),
                new ReverseTargetMechanic(),
                new ValueClassLevelMechanic(),       // value class level
                new CallMechanic(),                      // cast another skill
                //new DistanceFilterMechanic()        // distance filter
                new SoundCustomMechanic(),
                new ParticleAnimation(),
                new ParticleAnimationBukkit(),
                new ParticleAnimationMaterial(),
                new ParticleAnimationReflection(),
                new EffekActionMechanic()
        );
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<Trigger> getTriggers() {
        List<Trigger> list = Lists.newArrayList(
                new ManualTrigger()
        );
        list.removeIf(t -> ComponentRegistry.getTrigger(t.getKey()) != null);
        return list;
    }
}

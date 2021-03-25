package com.github.mrmks.sapi_extends.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ManualTriggerEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final String key;
    private final LivingEntity caster, target;
    private boolean match;
    public ManualTriggerEvent(String key, LivingEntity caster, LivingEntity target) {
        this.key = key;
        this.caster = caster;
        this.target = target;
        this.match = false;
    }

    public boolean isMatch(String nKey) {
        boolean t = key.equals(nKey);
        match = match || t;
        return t;
    }

    public boolean isMatch() {
        return match;
    }

    public LivingEntity getCaster() {
        return caster;
    }

    public LivingEntity getTarget() {
        return target;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

package com.github.mrmks.sapi_extends.bridge;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.serverct.ersha.jd.event.AttrEntityDamageEvent;

public class BridgeAttrDamageEvent extends Event implements Cancellable {

    private final static HandlerList handlers = new HandlerList();

    private AttrEntityDamageEvent event;
    public BridgeAttrDamageEvent(AttrEntityDamageEvent event) {
        this.event = event;
    }

    public AttrEntityDamageEvent getEvent() {
        return event;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return event.getCancelled();
    }

    @Override
    public void setCancelled(boolean b) {
        event.setCancelled(b);
    }
}

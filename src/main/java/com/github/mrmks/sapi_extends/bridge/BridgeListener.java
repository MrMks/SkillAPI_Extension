package com.github.mrmks.sapi_extends.bridge;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class BridgeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamaged(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            call(new BridgeDamageEntityEvent((EntityDamageByEntityEvent) event));
        } else if (event instanceof EntityDamageByBlockEvent) {
            call(new BridgeDamageBlockEvent((EntityDamageByBlockEvent) event));
        } else {
            call(new BridgeDamageEvent(event));
        }
    }

    private void call(BridgeDamageEvent event) {
        Bukkit.getPluginManager().callEvent(event);
    }
}

package com.github.mrmks.mc.sapi_extension.compound.mechanic;

import com.github.mrmks.mc.sapi_extension.EditorOptionHelper;
import com.github.mrmks.mc.sapi_extension.compound.CustomMechanic;
import com.google.common.collect.ImmutableList;
import com.sucy.skill.dynamic.custom.EditorOption;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DistanceFilterMechanic extends CustomMechanic {

    @Override
    public String getKey() {
        return "filter distance";
    }

    @Override
    public String getDescription() {
        return "Filter by distance";
    }

    @Override
    public List<EditorOption> getOptions() {
        return ImmutableList.of(
                EditorOptionHelper.dropdown("nearest", "return nearest target if true, farthest if false", ImmutableList.of("true", "false"))
        );
    }

    @Override
    public boolean execute(LivingEntity livingEntity, int i, List<LivingEntity> list) {
        if (list.isEmpty()) return false;
        final boolean nearest = settings.getBool("nearest", true);
        final Location loc = livingEntity.getLocation();
        LivingEntity found = list.stream()
                .min(Comparator.comparingDouble(
                        (o) -> nearest ? -loc.distanceSquared(o.getLocation()) : loc.distanceSquared(o.getLocation())))
                .orElse(null);
        if (found == null) return false;
        ArrayList<LivingEntity> sub = new ArrayList<>(1);
        sub.add(found);
        return executeChildren(livingEntity, i, sub);
    }

    @Override
    public boolean isContainer() {
        return true;
    }
}

package com.github.mrmks.mc.sapi_extension.compound;

import com.github.mrmks.mc.sapi_extension.Utils;
import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;

public abstract class CustomMechanic extends CustomEffectComponent {
    @Override
    public ComponentType getType() {
        return ComponentType.MECHANIC;
    }

    @Override
    public String getDisplayName() {
        return Utils.getDisplayName(getKey());
    }
}

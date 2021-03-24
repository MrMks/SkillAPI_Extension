package com.github.mrmks.sapi_extends.compound;

import com.sucy.skill.dynamic.ComponentType;
import com.sucy.skill.dynamic.custom.CustomEffectComponent;

public abstract class CustomTarget extends CustomEffectComponent {
    @Override
    public ComponentType getType() {
        return ComponentType.TARGET;
    }
}

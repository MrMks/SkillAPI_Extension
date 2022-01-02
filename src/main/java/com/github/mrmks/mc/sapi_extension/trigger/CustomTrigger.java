package com.github.mrmks.mc.sapi_extension.trigger;

import com.github.mrmks.mc.sapi_extension.Utils;
import com.sucy.skill.dynamic.ComponentType;
import org.bukkit.event.Event;

public abstract class CustomTrigger<E extends Event> implements com.sucy.skill.dynamic.custom.CustomTrigger<E> {

    private final String _key, _display;
    private final Class<E> _evtCls;
    protected CustomTrigger(String key, Class<E> evtClass) {
        this._key = key.toUpperCase().replace(' ', '_');
        this._display = Utils.getDisplayName(key.toLowerCase());
        this._evtCls = evtClass;
    }

    @Override
    public final ComponentType getType() {
        return ComponentType.TRIGGER;
    }

    @Override
    public final String getKey() {
        return _key;
    }

    @Override
    public final String getDisplayName() {
        return _display;
    }

    @Override
    public final Class<E> getEvent() {
        return _evtCls;
    }

}

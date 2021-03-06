package me.shreyasr.portals.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class MyPlayerComponent implements Component, Pool.Poolable {

    public static ComponentMapper<MyPlayerComponent> MAPPER
            = ComponentMapper.getFor(MyPlayerComponent.class);

    public static MyPlayerComponent create(PooledEngine engine) {
        return engine.createComponent(MyPlayerComponent.class);
    }

    public MyPlayerComponent() {
        reset();
    }

    @Override
    public void reset() {

    }
}

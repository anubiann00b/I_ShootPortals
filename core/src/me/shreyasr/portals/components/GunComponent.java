package me.shreyasr.portals.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class GunComponent implements Component, Pool.Poolable {

    public static ComponentMapper<GunComponent> MAPPER
            = ComponentMapper.getFor(GunComponent.class);

    public static GunComponent create(PooledEngine engine, int rounds, float fireTime) {
        GunComponent g = engine.createComponent(GunComponent.class);
        v.rounds = rounds;
        v.fireTime = fireTime;
        v.nextFireTime = 0;
        return v;
    }
    public int rounds;
    public float nextFireTime;
    public float fireTime;

    public GunComponent() {
        reset();
    }

    @Override
    public void reset() {
        rounds = 0;
        fireTime = 0;
        nextFireTime = 0;
    }
}


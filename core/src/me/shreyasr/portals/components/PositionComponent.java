package me.shreyasr.portals.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

import java.text.DecimalFormat;

public class PositionComponent implements Component, Pool.Poolable {

    private static DecimalFormat format = new DecimalFormat("#0.00");

    public static ComponentMapper<PositionComponent> MAPPER
            = ComponentMapper.getFor(PositionComponent.class);

    public static PositionComponent create(PooledEngine engine, float x, float y) {
        PositionComponent p = engine.createComponent(PositionComponent.class);
        p.x = x;
        p.y = y;
        return p;
    }

    public float x;
    public float y;

    @Override
    public String toString() {
        return "[" + format.format(x) + " " + format.format(y) + "]";
    }

    public PositionComponent() {
        reset();
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}

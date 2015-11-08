package me.shreyasr.portals.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class HitboxComponent implements Component, Pool.Poolable {

    public static ComponentMapper<HitboxComponent> MAPPER
            = ComponentMapper.getFor(HitboxComponent.class);

    public static HitboxComponent create(PooledEngine engine) {
        return create(engine, 0, 0, 0, 0);
    }

    public static HitboxComponent create(PooledEngine engine, int x, int y, int w, int h) {
        HitboxComponent hitbox = engine.createComponent(HitboxComponent.class);
        hitbox.rect = new Rectangle(x, y, w, h);
        return hitbox;
    }

    public Rectangle rect;

    public boolean intersects(HitboxComponent other) {
        return Intersector.overlaps(rect, other.rect);
    }

    @Override
    public String toString() {
        return rect.toString();
    }

    public HitboxComponent() {
        reset();
    }

    @Override
    public void reset() {
        if (rect != null) {
            rect.x = 0;
            rect.y = 0;
            rect.width = 0;
            rect.height = 0;
        }
    }
}

package me.shreyasr.portals.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import me.shreyasr.portals.components.AnimationComponent;
import me.shreyasr.portals.components.HitboxComponent;
import me.shreyasr.portals.components.MyPlayerComponent;
import me.shreyasr.portals.components.PositionComponent;
import me.shreyasr.portals.components.TextureComponent;
import me.shreyasr.portals.components.TextureTransformComponent;
import me.shreyasr.portals.components.VelocityComponent;

public class EntityFactory {

    public EntityFactory() {

    }

    public Entity createPlayer(PooledEngine engine, float x, float y) {
        Entity e = createDumbPlayer(engine, x, y);
        e.add(MyPlayerComponent.create(engine));
        return e;
    }

    private Entity createDumbPlayer(PooledEngine engine, float x, float y) {
        Entity e = engine.createEntity();

        e.add(AnimationComponent.create(engine, 1, 97, 172, 166));
        e.add(HitboxComponent.create(engine));
        e.add(PositionComponent.create(engine, x, y));
        e.add(TextureComponent.create(engine, Assets.PLAYER.getFile()));
        e.add(TextureTransformComponent.create(engine, 0.5f, 0.5f));
        e.add(VelocityComponent.create(engine, 0, 0));

        return e;
    }
}

package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.shreyasr.portals.PortalGame;
import me.shreyasr.portals.components.HitboxComponent;

public class DebugRenderSystem extends IteratingSystem {

    private final PortalGame game;

    public DebugRenderSystem(int priority, PortalGame game) {
        super(Family.all(HitboxComponent.class).get(), priority);
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HitboxComponent hitbox = HitboxComponent.MAPPER.get(entity);

        game.shape.set(ShapeRenderer.ShapeType.Line);

        game.shape.setColor(Color.RED);
        game.shape.rect(hitbox.rect.x, hitbox.rect.y, hitbox.rect.width, hitbox.rect.height);
    }
}

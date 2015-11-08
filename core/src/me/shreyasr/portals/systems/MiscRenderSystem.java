package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.shreyasr.portals.PortalGame;

public class MiscRenderSystem extends EntitySystem {

    private final PortalGame game;

    public MiscRenderSystem(int priority, PortalGame game) {
        super(priority);
        this.game = game;
    }

    @Override
    public void update(float deltaTime) {
        game.shape.set(ShapeRenderer.ShapeType.Line);

        game.shape.setColor(Color.RED);
        game.shape.rect(50, 50, 100, 100);
    }
}

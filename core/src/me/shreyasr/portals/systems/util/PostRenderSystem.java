package me.shreyasr.portals.systems.util;

import com.badlogic.ashley.core.EntitySystem;

import me.shreyasr.portals.PortalGame;

public class PostRenderSystem extends EntitySystem {

    private final PortalGame game;

    public PostRenderSystem(int priority, PortalGame game) {
        super(priority);
        this.game = game;
    }

    public void update(float deltaTime) {
        if (game.shape.isDrawing()) game.shape.end();
        if (game.batch.isDrawing()) game.batch.end();
    }
}

package me.shreyasr.portals.systems.util;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PreBatchRenderSystem extends EntitySystem {

    private final SpriteBatch batch;

    public PreBatchRenderSystem(int priority, SpriteBatch batch) {
        super(priority);
        this.batch = batch;
    }

    public void update(float deltaTime) {
        batch.begin();
    }
}

package me.shreyasr.portals.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

import me.shreyasr.portals.PortalGame;
import me.shreyasr.portals.systems.AnimationSystem;
import me.shreyasr.portals.systems.DebugRenderSystem;
import me.shreyasr.portals.systems.MainRenderSystem;
import me.shreyasr.portals.systems.PositionUpdateSystem;
import me.shreyasr.portals.systems.util.PostRenderSystem;
import me.shreyasr.portals.systems.util.PreBatchRenderSystem;
import me.shreyasr.portals.systems.util.ShapeRenderSystem;
import me.shreyasr.portals.util.EntityFactory;

public class GameScreen extends ScreenAdapter {

    private PortalGame game;

    public PooledEngine engine;

    public GameScreen(PortalGame game) {
        this.game = game;
    }

    private boolean initialized = false;

    @Override
    public void show() {
        engine = new PooledEngine();
        EntityFactory entityFactory = new EntityFactory();

        engine.addEntity(entityFactory.createPlayer(engine, 100, 100));

        int priority = 0;
        // @formatter:off
        engine.addSystem(new PositionUpdateSystem (++priority));
        engine.addSystem(new AnimationSystem      (++priority));

        engine.addSystem(new PreBatchRenderSystem (++priority, game.batch));
        engine.addSystem(new    MainRenderSystem  (++priority, game));
        engine.addSystem(new ShapeRenderSystem    (++priority, game.batch, game.shape));
        engine.addSystem(new    DebugRenderSystem (++priority, game));
        engine.addSystem(new PostRenderSystem     (++priority, game));
        // @formatter:on

        initialized = true;
    }

    @Override
    public void render(float delta) {
        if (!initialized) return;
        engine.update(Gdx.graphics.getRawDeltaTime() * 1000);
    }
}

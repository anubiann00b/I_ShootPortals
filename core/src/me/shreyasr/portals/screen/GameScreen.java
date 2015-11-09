package me.shreyasr.portals.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.shreyasr.portals.PortalGame;
import me.shreyasr.portals.systems.AnimationSystem;
import me.shreyasr.portals.systems.CameraUpdateSystem;
import me.shreyasr.portals.systems.DebugRenderSystem;
import me.shreyasr.portals.systems.GravitySystem;
import me.shreyasr.portals.systems.MainRenderSystem;
import me.shreyasr.portals.systems.MiscRenderSystem;
import me.shreyasr.portals.systems.MyPlayerMovementSystem;
import me.shreyasr.portals.systems.PositionUpdateSystem;
import me.shreyasr.portals.systems.TileRenderSystem;
import me.shreyasr.portals.systems.util.PostRenderSystem;
import me.shreyasr.portals.systems.util.PreBatchRenderSystem;
import me.shreyasr.portals.systems.util.ShapeRenderSystem;
import me.shreyasr.portals.util.Assets;
import me.shreyasr.portals.util.EntityFactory;

public class GameScreen extends ScreenAdapter {

    private PortalGame game;

    public PooledEngine engine;
    public OrthographicCamera camera;
    public Viewport viewport;

    public GameScreen(PortalGame game) {
        this.game = game;
    }

    private boolean initialized = false;

    @Override
    public void show() {
        camera = new OrthographicCamera(640, 480);
        viewport = new ExtendViewport(800, 600, 1280, 720, camera);

        TiledMap map = game.assetManager.get(Assets.MAP.getFile());

        engine = new PooledEngine();
        EntityFactory entityFactory = new EntityFactory();

        engine.addEntity(entityFactory.createPlayer(engine, 200, 200));

        int priority = 0;
        // @formatter:off
        engine.addSystem(new GravitySystem          (++priority));
        engine.addSystem(new MyPlayerMovementSystem (++priority));
        engine.addSystem(new PositionUpdateSystem   (++priority, map));
        engine.addSystem(new AnimationSystem        (++priority));

        engine.addSystem(new CameraUpdateSystem   (++priority, game.batch, camera, viewport));
        engine.addSystem(new TileRenderSystem     (++priority, game, map, camera));
        engine.addSystem(new PreBatchRenderSystem (++priority, game.batch));
        engine.addSystem(new    MainRenderSystem  (++priority, game));
        engine.addSystem(new ShapeRenderSystem    (++priority, game.batch, game.shape, camera));
        engine.addSystem(new    MiscRenderSystem  (++priority, game));
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

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}

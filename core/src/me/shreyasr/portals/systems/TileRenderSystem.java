package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import me.shreyasr.portals.PortalGame;

public class TileRenderSystem extends EntitySystem {

    private final PortalGame game;
    private final OrthographicCamera camera;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;

    public TileRenderSystem(int priority, PortalGame game, TiledMap map, OrthographicCamera camera) {
        super(priority);
        this.game = game;
        this.map = map;
        this.camera = camera;
        renderer = new OrthogonalTiledMapRenderer(map, 4f, game.batch);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
    }
}
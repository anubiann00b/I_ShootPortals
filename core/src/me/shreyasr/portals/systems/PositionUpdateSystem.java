package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

import me.shreyasr.portals.components.HitboxComponent;
import me.shreyasr.portals.components.PositionComponent;
import me.shreyasr.portals.components.VelocityComponent;

public class PositionUpdateSystem extends IteratingSystem {

    private TiledMap map;

    public PositionUpdateSystem(int priority, TiledMap map) {
        super(
                Family.all(PositionComponent.class,
                           VelocityComponent.class)
                      .get(),
                priority);
        this.map = map;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = PositionComponent.MAPPER.get(entity);
        VelocityComponent vel = VelocityComponent.MAPPER.get(entity);
        HitboxComponent hitbox = HitboxComponent.MAPPER.get(entity);

        double speedMult = (deltaTime * 60 / 1000) * 1;

        List<Rectangle> tiles = new ArrayList<Rectangle>();

        TiledMapTileLayer tileLayer = ((TiledMapTileLayer)map.getLayers().get(0));
        for (int i = 0; i < tileLayer.getWidth(); i++) {
            for (int j = 0; j < tileLayer.getHeight(); j++) {
                if (tileLayer.getCell(i,j) != null) {
                    Rectangle rect = new Rectangle(
                            tileLayer.getTileWidth()*4*i, tileLayer.getTileHeight()*4*j,
                            tileLayer.getTileWidth()*4, tileLayer.getTileHeight()*4);
                    tiles.add(rect);
                }
            }
        }

        Rectangle intersection = new Rectangle();

        for (Rectangle rect : tiles) {
            if (Intersector.intersectRectangles(hitbox.rect, rect, intersection)) {
                if (rect.y > hitbox.rect.y && hitbox.rect.y+hitbox.rect.height < rect.y+rect.height) {
                    pos.y -= intersection.height;
                    vel.dy = 0;
                }
                if (rect.y < hitbox.rect.y) {
                    pos.y += intersection.height;
                    vel.dy = 0;
                }

                if (rect.x > hitbox.rect.x && hitbox.rect.x+hitbox.rect.width < rect.x+rect.width) {
                    pos.x -= intersection.width;
                    vel.dx = 0;
                }
                if (rect.x < hitbox.rect.x) {
                    pos.x += intersection.width;
                    vel.dx = 0;
                }
            }
        }

        pos.x += vel.dx * speedMult;
        pos.y += vel.dy * speedMult;
    }
}

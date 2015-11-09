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

        List<Polygon> tiles = new ArrayList<Polygon>();

        TiledMapTileLayer tileLayer = ((TiledMapTileLayer)map.getLayers().get(0));
        for (int i = 0; i < tileLayer.getWidth(); i++) {
            for (int j = 0; j < tileLayer.getHeight(); j++) {
                if (tileLayer.getCell(i,j) != null) {
                    Polygon rect = new Polygon(new float[]{
                            tileLayer.getTileWidth()*4*i, tileLayer.getTileHeight()*4*j,
                            tileLayer.getTileWidth()*4*(i+1), tileLayer.getTileHeight()*4*(j+1)
                        });
                    tiles.add(rect);
                }
            }
        }
<<<<<<< HEAD

        Rectangle intersectionNext = new Rectangle();
        Rectangle intersection = new Rectangle();

        for (Rectangle rect : tiles) {
            Rectangle nextRect = new Rectangle(hitbox.rect);
            nextRect.x += vel.dx;
            nextRect.y += vel.dy;
            if (!Intersector.overlaps(hitbox.rect, rect) && Intersector.intersectRectangles(nextRect, rect, intersectionNext)) {
                if (rect.y > nextRect.y && nextRect.y + nextRect.height < rect.y + rect.height) {
                    if (tileLayer.getCell((int)(0.5+rect.x/tileLayer.getTileWidth()), -1+(int)(0.5+rect.y/tileLayer.getTileHeight())) == null) {
                        pos.y += vel.dy - intersectionNext.height;
                        vel.dy = 0;
                    }
                } else if (rect.y < nextRect.y) {
                    if (tileLayer.getCell((int)(0.5+rect.x/tileLayer.getTileWidth()), 1+(int)(0.5+rect.y/tileLayer.getTileHeight())) == null) {
                        pos.y += vel.dy + intersectionNext.height;
                        vel.dy = 0;
                    }
                } if (rect.x > nextRect.x && rect.x + nextRect.width < rect.x + rect.width) {
                    if (tileLayer.getCell((int)(0.5+nextRect.x/tileLayer.getTileWidth())-1, (int)(0.5+rect.y/tileLayer.getTileHeight())) == null) {
                        pos.x += vel.dx - intersectionNext.width;
                        vel.dx = 0;
                    }
                } else if (rect.x < nextRect.x) {
                    if (tileLayer.getCell((int)(0.5+rect.x/tileLayer.getTileWidth())+1, (int)(0.5+rect.y/tileLayer.getTileHeight())) == null) {
                        pos.x += vel.dx + intersectionNext.width;
                        vel.dx = 0;
                    }
                }
=======
        List<Polygon> hits = new ArrayList<Polygon>();
        for (Polygon rect : tiles) {
            if (Intersector.intersectLinePolygon(
                                                new Vector2(pos.x,pos,y),
                                                new Vector2(something, something),
                                                rect
                                                )) {
                hits.add(rect);
>>>>>>> b1481acd094b0c2634659a1bb6eb3ff87455c99b
            }
        }
        Polygon best;
        for(Polygon rect : hits){

        }

    }
}

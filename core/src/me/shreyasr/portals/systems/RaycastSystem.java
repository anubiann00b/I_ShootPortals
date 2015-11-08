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

public class RaycastSystem extends IteratingSystem {

    private TiledMap map;

    public RaycastSystem(int priority, TiledMap map) {
        super(
                Family.all(MyPlayerComponent.class)
                      .get(),
                priority);
        this.map = map;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(Gdx.input.isButtonPressed(Input.Buttons.Left)){
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
            List<Polygon> hits = new ArrayList<Polygon>();
            for (Polygon rect : tiles) {
                if (Intersector.intersectLinePolygon(
                                                    new Vector2(pos.x,pos,y),
                                                    new Vector2(something, something),
                                                    rect
                                                    )) {
                    hits.add(rect);
                }
            }
            Polygon best;
            float bestDist;
            for(Polygon rect : hits){
                float dist = distance(rect.getX(),rect.getY(),pos.x,pos.y);
                if(dist < bestDist){
                    bestDist = dist;
                    best = rect;
                }
            }
            ifjweioasjiof
            //create portal here
        }
    }
    private void distance(float x1, float y1, float x2, float y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
}

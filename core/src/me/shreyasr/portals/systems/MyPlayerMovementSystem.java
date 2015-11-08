package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import me.shreyasr.portals.PortalGame;
import me.shreyasr.portals.components.MyPlayerComponent;
import me.shreyasr.portals.components.PositionComponent;
import me.shreyasr.portals.components.VelocityComponent;

public class MyPlayerMovementSystem extends IteratingSystem {

    public MyPlayerMovementSystem(int priority) {
        super(
                Family.all(MyPlayerComponent.class,
                           PositionComponent.class,
                           VelocityComponent.class)
                        .get(),
                priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = PositionComponent.MAPPER.get(entity);
        VelocityComponent vel = VelocityComponent.MAPPER.get(entity);

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            vel.dx = 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            vel.dx = -1;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            vel.dy += PortalGame.JUMP_POWER;
        }
    }
}
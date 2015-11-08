package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.shreyasr.portals.PortalGame;
import me.shreyasr.portals.components.VelocityComponent;

public class GravitySystem extends IteratingSystem {

    public GravitySystem(int priority) {
        super(
                Family.all(VelocityComponent.class)
                        .get(),
                priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent vel = VelocityComponent.MAPPER.get(entity);
        vel.dy -= PortalGame.GRAVITY_CONSTANT;
    }
}

package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.shreyasr.portals.components.PositionComponent;
import me.shreyasr.portals.components.VelocityComponent;

public class PositionUpdateSystem extends IteratingSystem {

    public PositionUpdateSystem(int priority) {
        super(
                Family.all(PositionComponent.class,
                           VelocityComponent.class)
                      .get(),
                priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = PositionComponent.MAPPER.get(entity);
        VelocityComponent vel = VelocityComponent.MAPPER.get(entity);

        double speedMult = (deltaTime * 60 / 1000) * 1;

        pos.x += vel.dx * speedMult;
        pos.y += vel.dy * speedMult;
    }
}

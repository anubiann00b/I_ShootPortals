package me.shreyasr.portals.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.shreyasr.portals.components.AnimationComponent;
import me.shreyasr.portals.components.HitboxComponent;
import me.shreyasr.portals.components.PositionComponent;
import me.shreyasr.portals.components.TextureTransformComponent;
import me.shreyasr.portals.components.VelocityComponent;

public class AnimationSystem extends IteratingSystem {

    public AnimationSystem(int priority) {
        super(
                Family.all(AnimationComponent.class,
                           TextureTransformComponent.class)
                      .get(),
                priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent anim = AnimationComponent.MAPPER.get(entity);
        TextureTransformComponent transform = TextureTransformComponent.MAPPER.get(entity);
        VelocityComponent vel = VelocityComponent.MAPPER.get(entity);

        if (vel == null || (vel.dx == 0 && vel.dy == 0)) {
            anim.timeSinceLastFrame = 0;
            anim.currentFrame = 0;
        } else {
            anim.timeSinceLastFrame += deltaTime;
            if (anim.timeSinceLastFrame >= anim.frameTime) {
                anim.timeSinceLastFrame = 0;
                anim.currentFrame++;
                if (anim.currentFrame >= anim.length) {
                    anim.currentFrame = 0;
                }
            }
        }

        transform.screenWidth = (int) (anim.frameWidth * transform.scaleX);
        transform.screenHeight = (int) (anim.frameHeight * transform.scaleY);
        transform.originX = transform.screenWidth/2;
        transform.originY = transform.screenHeight/2;
        transform.srcX = anim.currentFrame * anim.frameWidth;
        transform.srcY = 0;
        transform.srcWidth = anim.frameWidth;
        transform.srcHeight = anim.frameHeight;
        transform.rotation = 0;

        PositionComponent pos = PositionComponent.MAPPER.get(entity);
        HitboxComponent hitbox = HitboxComponent.MAPPER.get(entity);
        hitbox.rect.x = pos.x - transform.originX;
        hitbox.rect.y = pos.y - transform.originY;
        hitbox.rect.width = anim.frameWidth*transform.scaleX;
        hitbox.rect.height = anim.frameHeight*transform.scaleY;
    }
}

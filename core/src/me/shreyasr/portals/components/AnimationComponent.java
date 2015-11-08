package me.shreyasr.portals.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class AnimationComponent implements Component, Pool.Poolable {

    public static ComponentMapper<AnimationComponent> MAPPER
            = ComponentMapper.getFor(AnimationComponent.class);

    public static AnimationComponent create(PooledEngine engine, int length,
                                            int frameWidth, int frameHeight, int frameTime) {
        AnimationComponent anim = engine.createComponent(AnimationComponent.class);
        anim.length = length;
        anim.frameWidth = frameWidth;
        anim.frameHeight = frameHeight;
        anim.frameTime = frameTime;
        return anim;
    }

    public int length; // number of frames in an animation
    public int frameWidth;  // width of a frame, in pixels
    public int frameHeight; // height of a frame, in pixels

    public int frameTime; // milliseconds per frame
    public int timeSinceLastFrame; // milliseconds since last frame

    public int currentFrame; // current frame

    public AnimationComponent() {
        reset();
    }

    @Override
    public void reset() {
        length = 0;
        frameWidth = 0;
        frameHeight = 0;
        frameTime = 0;
        timeSinceLastFrame = 0;
        currentFrame = 0;
    }
}

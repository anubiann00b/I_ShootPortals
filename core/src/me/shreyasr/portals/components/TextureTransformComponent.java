package me.shreyasr.portals.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

public class TextureTransformComponent implements Component, Pool.Poolable {

    public static ComponentMapper<TextureTransformComponent> MAPPER
            = ComponentMapper.getFor(TextureTransformComponent.class);

    public static TextureTransformComponent create(PooledEngine engine, float scaleX, float scaleY) {
        TextureTransformComponent ttc = engine.createComponent(TextureTransformComponent.class);
        ttc.scaleX = scaleX;
        ttc.scaleY = scaleY;
        return ttc;
    }

    public int screenWidth;
    public int screenHeight;

    public int originX;
    public int originY;

    public int srcX;
    public int srcY;
    public int srcWidth;
    public int srcHeight;

    public float scaleX;
    public float scaleY;

    public float rotation;

    public boolean hide;

    public Color color;

    public TextureTransformComponent() {
        reset();
    }

    @Override
    public void reset() {
        screenWidth = 0;
        screenHeight = 0;
        originX = 0;
        originY = 0;
        srcX = 0;
        srcY = 0;
        srcWidth = 0;
        srcHeight = 0;
        scaleX = 0;
        scaleY = 0;
        rotation = 0;
        hide = false;
        color = Color.WHITE;
    }
}


package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    protected PolygonSprite poly;
    protected Texture textureSolid;
    protected Vector2 pos;
    protected float angle;

    public PolygonSprite getPrimitive() {
        return poly;
    }

    public void setPosition(Vector2 nextPos) {
        pos = nextPos;
        if (nextPos.x >= Gdx.graphics.getWidth() + 20)
            nextPos.x = 10;
        if (nextPos.x <= -40)
            nextPos.x = Gdx.graphics.getWidth() - 20f;
        if (nextPos.y >= Gdx.graphics.getHeight() + 20)
            nextPos.y = 10;
        if (nextPos.y <= -40)
            nextPos.y = Gdx.graphics.getHeight() - 20f;
        poly.setPosition(nextPos.x, nextPos.y);
    }

    public Vector2 getPosition() {
        return pos;
    }

    public void setRotation(float angle) {
        this.angle = angle;
        poly.setRotation(angle);
    }

    public float getRotation() {
        return angle;
    }

    public float[] shoot() {
        return new float[]{pos.x, pos.y, angle};
    }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {
    private long elapsedTime;

    public Bullet(Vector2 pos, float angle) {
        this.angle = angle;
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.CYAN);
        pix.fill();
        textureSolid = new Texture(pix);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[]{
                        10, 10, // 0
                        10, 20, // 1
                        20, 20, // 2
                        20, 10  // 3
                }, new short[]{
                0, 3, 2,
                0, 2, 1});
        poly = new PolygonSprite(polyReg);
        poly.setOrigin(15, 15);
        this.setPosition(pos);
        this.setRotation(angle);
        elapsedTime = System.currentTimeMillis();
    }

    public void update() {
        float speed = 7;
        Vector2 pos = this.getPosition();
        Vector2 normalizedCircle = new Vector2();
        normalizedCircle.y = (float) Math.sin((angle + 90) * Math.PI / 180);
        normalizedCircle.x = (float) Math.cos((angle + 90) * Math.PI / 180);
        pos.x += speed * normalizedCircle.x;
        pos.y += speed * normalizedCircle.y;
        this.setPosition(pos);
    }

    public boolean shouldDie() {
        return System.currentTimeMillis() - elapsedTime > 3_000 || isXPositionInBound() || isYPositionInBound();
    }

    private boolean isYPositionInBound() {
        return Math.abs(pos.y - Gdx.graphics.getHeight()) < 10 || pos.y < 10;
    }

    private boolean isXPositionInBound() {
        return Math.abs(pos.x - Gdx.graphics.getWidth()) < 10 || pos.x < 10;
    }
}

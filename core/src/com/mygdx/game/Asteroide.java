package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Asteroide extends Entity {
    private static final Random gen = new Random();
    private Vector2 newDistance;
    private float xCircle = 0;
    private float yCircle = 0;
    private float radius;

    public Asteroide(Vector2 playerPos) {
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(generateColor());
        pix.fill();
        textureSolid = new Texture(pix);

        ArrayList<Vector2> floats = new ArrayList<>();
        for (float x = 0; x <= Math.PI * 2; x += 0.1f)
            floats.add(new Vector2((float) (Math.cos(x) * 0.6f + gen.nextFloat()),
                    (float) (Math.sin(x) * 0.7f + gen.nextFloat())));

        float XCircle = (float) Gdx.graphics.getWidth() % (gen.nextFloat() * 10);
        float YCircle = (float) Gdx.graphics.getHeight() % (gen.nextFloat() * 10);

        radius = (gen.nextFloat() * 30 + 6) + 25;
        float[] points = new float[2 + (floats.size() * 2)];

        for (Vector2 e : floats) {
            e.x *= radius;
            e.y *= radius;
            e.x += XCircle;
            e.y += YCircle;
            e.x *= (gen.nextFloat());
            e.y *= (gen.nextFloat());
        }

        int id = 0;
        points[id++] = XCircle;
        points[id++] = YCircle;

        for (Vector2 aFloat : floats) {
            points[id++] = aFloat.x;
            points[id++] = aFloat.y;
        }

        int pointsMaxSize = 2 + (floats.size() * 2);
        short[] triangles = new short[pointsMaxSize / 3];

        id = 0;
        for (int x = 1; id < (int) Math.floor(pointsMaxSize / 3.0); x += 3) {
            triangles[id++] = 0;
            triangles[id++] = (short) x;
            triangles[id++] = (short) (x + 1);
        }

        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid), points, triangles);

        poly = new PolygonSprite(polyReg);
        poly.setOrigin(0, 0);

        this.pos = generatePosition();
        this.setPosition(pos);

        newDistance = new Vector2();
        newDistance.x = -(this.pos.x - playerPos.x) / (this.pos.x + playerPos.x);
        newDistance.y = -(this.pos.y - playerPos.y) / (this.pos.y + playerPos.y);
    }

    private Vector2 generatePosition() {
        float x = gen.nextFloat() * Gdx.graphics.getWidth();
        float y = gen.nextFloat() * Gdx.graphics.getHeight();
        boolean xIsRandom = gen.nextBoolean();

        List<Integer> borderValueX = new ArrayList<>();
        borderValueX.add(0);
        borderValueX.add(Gdx.graphics.getWidth());

        List<Integer> borderValueY = new ArrayList<>();
        borderValueY.add(0);
        borderValueY.add(Gdx.graphics.getWidth());

        return xIsRandom ? new Vector2(borderValueX.get(gen.nextInt(2)), y)
                : new Vector2(x, borderValueY.get(gen.nextInt(2)));
    }

    private Color generateColor() {
        float valueR = Math.abs((gen.nextInt(170) / 255f) + 0.1f);
        float valueG = Math.abs((gen.nextInt(180) / 255f) + 0.1f);
        float valueB = Math.abs((gen.nextInt(120) / 255f) + 0.1f);

        return new Color(valueR, valueG, valueB, 0.8f);
    }

    @Override
    public void setPosition(Vector2 v2) {
        super.setPosition(v2);

        xCircle = v2.x;
        yCircle = v2.y;
    }

    public boolean isBulletInCollision(Bullet bullet) {
        Vector2 bulleta = bullet.getPosition();
        return isInCollision(bulleta);
    }

    public void update() {
        Vector2 direction = this.getPosition();

        direction.y += newDistance.y * 3f;
        direction.x += newDistance.x * 3f;

        setPosition(direction);
    }

    public boolean isInCollision(Vector2 bulleta) {
        if (xCircle + radius > bulleta.x && xCircle - radius < bulleta.x)
            return yCircle + radius > bulleta.y && yCircle - radius < bulleta.y;
        return false;
    }

    public boolean shouldDealDmg(Vector2 playerPos) {
        return isInCollision(playerPos);
    }

}

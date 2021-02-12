package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Ship extends Entity {

    private int score;
    private int health;

    public Ship() {
        angle = 0;
        pos = new Vector2(0, 0);
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.WHITE);
        pix.fill();
        textureSolid = new Texture(pix);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[]{
                        1, 1,
                        25, 75,
                        50, 1,
                        25, 15,

                }, new short[]{
                0, 3, 1,
                2, 3, 1});
        poly = new PolygonSprite(polyReg);
        poly.setOrigin(25, 37.5f);

        score = 0;
        health = 3;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public boolean shouldDie() {
        return health == 0;
    }

    public void dealDmg(int x) {
        health -= x;
    }


}

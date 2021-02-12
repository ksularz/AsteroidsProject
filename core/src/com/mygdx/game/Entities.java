package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class Entities {
    private static final int MAX_ASTEROIDS = 8;

    private ArrayList<Entity> entities;
    private Ship playerEntity;
    private long elapsedTime;

    Entities() {
        entities = new ArrayList<>();
        playerEntity = new Ship();
        playerEntity.setPosition(new Vector2(620, 335));
        entities.add(playerEntity);
        entities.add(new Asteroide(playerEntity.getPosition()));
        elapsedTime = System.currentTimeMillis();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Ship getPlayerEntity() {
        return playerEntity;
    }

    public void addActiveEntity(float[] e) {
        Vector2 pos = new Vector2(e[0] + 10, e[1] + 30);
        float angle = e[2];

        entities.add(new Bullet(pos, angle));
    }

    public void updateEntities() {
        int counter = 0;
        ArrayList<Bullet> bullets = new ArrayList<>();
        ArrayList<Asteroide> asteroids = new ArrayList<>();

        for (Entity e : entities) {
            if (e instanceof Asteroide) {
                ((Asteroide) e).update();
                counter++;
                asteroids.add((Asteroide) e);
            } else if (e instanceof Bullet) {
                updateBullet((Bullet) e);
                bullets.add((Bullet) e);
            }
        }

        checkBulletCollisions(bullets, asteroids);
        if (playerEntity.shouldDie()) {
            System.out.println("Your score is " + playerEntity.getScore());
            System.exit(0);
        }
        for (Asteroide e : asteroids)
            if (e.shouldDealDmg(playerEntity.getPosition()) && System.currentTimeMillis() - elapsedTime > 1000) {
                playerEntity.dealDmg(1);
                elapsedTime = System.currentTimeMillis();
            }

        for (int x = 0; x + counter <= MAX_ASTEROIDS; x++)
            entities.add(new Asteroide(playerEntity.getPosition()));
    }

    private void checkBulletCollisions(ArrayList<Bullet> bullets, ArrayList<Asteroide> asteroids) {
        for (Asteroide a : asteroids) {
            for (Bullet bullet : bullets)
                if (a.isBulletInCollision(bullet)) {
                    entities.remove(a);
                    entities.remove(bullet);
                    playerEntity.incrementScore();
                    break;
                } else {
                    if (bullet.shouldDie())
                        entities.remove(bullet);
                }
        }
    }

    private void updateBullet(Bullet bullet) {
        bullet.update();
    }
}
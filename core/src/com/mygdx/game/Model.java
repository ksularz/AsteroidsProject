package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Model extends DualLink {
    private Entities entities;
    private boolean isWPressed = false;
    private boolean isSPressed = false;
    private boolean isAPressed = false;
    private boolean isDPressed = false;

    BitmapFont font = new BitmapFont();

    Model() {
        entities = new Entities();
        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {

                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {

                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Keys.W:
                        isWPressed = false;
                        break;
                    case Keys.S:
                        isSPressed = false;
                        break;
                    case Keys.A:
                        isAPressed = false;
                        break;
                    case Keys.D:
                        isDPressed = false;
                        break;

                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {

                return false;
            }

            @Override
            public boolean keyDown(int keycode) {

                switch (keycode) {
                    case Keys.W:
                        isWPressed = true;
                        break;
                    case Keys.S:
                        isSPressed = true;
                        break;
                    case Keys.A:
                        isAPressed = true;
                        break;
                    case Keys.D:
                        isDPressed = true;
                        break;
                    case Keys.SPACE:
                        entities.addActiveEntity(entities.getPlayerEntity().shoot());
                        break;
                }


                return false;
            }
        });
    }


    @Override
    public void onNotify(Subject s) {


    }


    public void drawEntities(PolygonSpriteBatch psb) {
        for (Entity e : entities.getEntities())
            e.getPrimitive().draw(psb);
        font.draw(psb, "Score: " + entities.getPlayerEntity().getScore(), 50, Gdx.graphics.getHeight() - 25f);
        font.draw(psb, "Lives: " + entities.getPlayerEntity().getHealth(), 50, Gdx.graphics.getHeight() - 50f);
    }

    public void update() {
        entities.updateEntities();

    }

    public void checkForInput() {
        if (!isWPressed && !isSPressed && !isAPressed && !isDPressed)
            return;
        Ship player = entities.getPlayerEntity();
        Vector2 lastPos = player.getPosition();
        float distance = 0;
        float angle = player.getRotation();
        if (isWPressed && isSPressed)
            return;
        if (isAPressed && isDPressed)
            return;
        if (isWPressed)
            distance = 5;
        if (isSPressed)
            distance = -5;
        if (isAPressed)
            angle += 5;
        if (isDPressed)
            angle -= 5;
        Vector2 normalizedCircle = new Vector2();
        normalizedCircle.y = (float) Math.sin((angle + 90) * Math.PI / 180);
        normalizedCircle.x = (float) Math.cos((angle + 90) * Math.PI / 180);
        lastPos.x += distance * normalizedCircle.x;
        lastPos.y += distance * normalizedCircle.y;
        player.setPosition(lastPos);
        player.setRotation(angle);
    }

}

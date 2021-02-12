package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

public class View extends DualLink {
    Model model;

    public View() {
        model = new Model();
        model.addObserver(this);
    }

    @Override
    public void onNotify(Subject s) {
    }

    public void render(PolygonSpriteBatch psb) {
        model.checkForInput();
        psb.begin();
        model.drawEntities(psb);
        psb.end();
        model.update();
    }
}

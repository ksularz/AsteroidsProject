package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public interface Shape {

	public boolean isInCollision();
	public ArrayList<Vector2> draw();
	
	
	
}

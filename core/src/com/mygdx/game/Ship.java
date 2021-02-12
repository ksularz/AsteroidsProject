package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Ship implements Shape{

	float x;
	float y;
	float size;
	
	@Override
	public boolean isInCollision() {
		return false;}
	public ArrayList<Vector2> draw() {
		return new ArrayList<Vector2>();}
	
	public Ship(float x,float y, float size){
		this.x=x;
		this.y=y;
		this.size=size;
		
		
		
		
	}
	
}

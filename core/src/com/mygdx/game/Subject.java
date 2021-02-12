package com.mygdx.game;

import java.util.ArrayList;

public class Subject {

	ArrayList<Observer> observers;
	public Subject() {
		observers=new ArrayList<Observer>();
	}
	
	public void AddObserver(Observer o) {
		observers.add(o);
	}
	
	public void NotifyAllObserver() {
		for(Observer o:observers)
			o.OnNotify(this);
	}
	
}

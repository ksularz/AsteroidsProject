package com.mygdx.game;

import java.util.ArrayList;

public class Subject {

	private ArrayList<Observer> observers;

	public Subject() {
		observers=new ArrayList<>();
	}

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void notifyAllObserver() {
		for(Observer o:observers)
			o.onNotify(this);
	}

}

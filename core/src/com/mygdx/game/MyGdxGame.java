package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;



public class MyGdxGame extends ApplicationAdapter implements Observer {
	SpriteBatch batch;
	Texture img;

	ShapeRenderer shapeRenderer;
	ArrayList<Shape> shapes;
	
	PolygonSprite poly;
	PolygonSpriteBatch polyBatch;
	Texture textureSolid;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shapeRenderer=new ShapeRenderer() ;
		View view=new View();
		view.AddObserver(this);
		Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGBA8888);
		pix.setColor(0xDEFDE5);
		pix.fill();
		textureSolid = new Texture(pix);
		PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
				  new float[] {      // Four vertices
						    1, 1,            // Vertex 0         3--2
						    25, 75,          // Vertex 1         | /|
						    50, 1,        // Vertex 2         |/ |         
						    25,15,
						    1, 1
						}, new short[] {
						   0,3,1,
						   2,3,1
						   
						   // Two triangles using vertex indices. 
						});
//		 shapeRenderer.line(1, 1, 25, 75);
//		 shapeRenderer.line(50, 1, 25, 75);
//		 shapeRenderer.line(1, 1, 25, 15);
//		 shapeRenderer.line(50, 1, 25, 15);
		poly = new PolygonSprite(polyReg);
		poly.setOrigin(0, 0);
		polyBatch = new PolygonSpriteBatch();
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
//		batch.begin();
//		batch.draw(shapeRenderer, 0, 0);
		
//		batch.end();
		polyBatch.begin();
		poly.draw(polyBatch);
//		poly.rotate(70);
//		poly.draw(polyBatch);
//		poly.setRotation(0);
		poly.setPosition(285, 225);
		polyBatch.end();
		
		 
//		 shapeRenderer.begin(ShapeType.Line);
//		 shapeRenderer.setColor(250,250,250,1);

		 
		 
	//	 shapeRenderer.rect(x, y, width, height);
		// shapeRenderer.circle(x, y, radius);
		 shapeRenderer.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void OnNotify(Subject s) {
		
		
	}
}

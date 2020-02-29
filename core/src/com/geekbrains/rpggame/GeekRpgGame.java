package com.geekbrains.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GeekRpgGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private  Hero hero;
	private Texture textureGrass;
	private Texture texturePointer;
	private Vector2 pointerPosition;
	private  float rt;

	@Override
	public void create () {
		batch = new SpriteBatch();
		hero = new Hero();
		textureGrass = new Texture("grass.png");
		texturePointer = new Texture("pointer.png");
		pointerPosition = new Vector2(0,0);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 8; j++) {
				batch.draw(textureGrass, i * 80,j * 80);
			}
		}
		batch.draw(texturePointer, pointerPosition.x - 32, pointerPosition.y - 32,32,32,64,64,1,1,rt,0,0,64,64,false,false);
		hero.render(batch);
		batch.end();
	}

	public void update(float dt){
		rt += dt * 90;
		hero.update(dt);

		if(Gdx.input.isTouched()){
			pointerPosition.set(Gdx.input.getX(), 640 - Gdx.input.getY());
		}
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

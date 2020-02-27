package com.geekbrains.rpggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private float param;


    public  Hero(){
        this.texture = new Texture("hero.png");
        this.position = new Vector2(100, 100);
        this.speed = 100.0f;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x - 32, position.y - 32, 32, 32, 64, 64, 1, 1, 0,0,0,64,64,false,false);
    }

    public void update(float dt){
//        if(Gdx.input.justTouched()){
//            position.set(Gdx.input.getX(), 640 - Gdx.input.getY());
//        }
//        for (int i = 0; i < Gdx.input.getX() ; i++) {
//            position.x += speed * dt;
//        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            position.x -= speed * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            position.x += speed * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            position.y += speed * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            position.y -= speed * dt;
        }

    }
}

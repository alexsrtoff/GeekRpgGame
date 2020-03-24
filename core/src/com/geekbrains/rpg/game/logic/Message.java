package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.Vector2;

public class Message {
    private Vector2 position;
    private String message;

    public Message() {
        this.position = new Vector2(0,0);
        this.message = " ";
    }

    public void setup(float x, float y, String message){
        this.position.set(x,y);
        this.message = message;
    }

    public Vector2 getPosition() {
        return position;
    }



    public String getMessage() {
        return message;
    }
}

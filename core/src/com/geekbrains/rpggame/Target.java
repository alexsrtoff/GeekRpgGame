package com.geekbrains.rpggame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Target {
    private TextureRegion textureRegion;

    public Vector2 getPosition() {
        return position;
    }

    private Vector2 position;

    public Target(TextureAtlas atlas){
        this.textureRegion = atlas.findRegion("pointer");
        setPosition();
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureRegion, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 1);
    }

    public void setPosition(){
        this.position = new Vector2((float)Math.random()*1040, 640 -(float)Math.random()*640);
    }
}


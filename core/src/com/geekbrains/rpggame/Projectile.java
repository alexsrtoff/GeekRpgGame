package com.geekbrains.rpggame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private TextureRegion textureRegion;
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;

    public Projectile(TextureAtlas atlas) {
        this.textureRegion = atlas.findRegion("arrow");
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.active = false;
    }

    public void setup(float x, float y, float targetX, float targetY) {
        if (!active) {
            position.set(x, y);
            velocity.set(targetX, targetY).sub(x, y).nor().scl(800.0f);
            active = true;
        }
    }

    public void deactivate() {
        active = false;
    }

    public void render(SpriteBatch batch) {
        if (active) {
            batch.draw(textureRegion, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, velocity.angle());
        }
    }

    public void update(float dt, Target target) {
        if (active) {
            position.mulAdd(velocity, dt);
            if (position.dst(target.getPosition()) < 30) {
                target.setPosition();
            }

            if (position.x < 0 || position.x > 1040 || position.y < 0 || position.y > 640) {
                deactivate();
            }
        }
    }

}

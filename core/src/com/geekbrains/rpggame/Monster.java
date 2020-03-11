package com.geekbrains.rpggame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Monster {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private TextureRegion textureHp;
    private Vector2 position;
    private Vector2 dst;
    private Vector2 tmp;
    private float lifetime;
    private float atackTime;
    private float speed;
    private int hp;
    private int hpMax;

    public void setPosition() {
        position.set((float)Math.random()*1040, 640 -(float)Math.random()*640);
        hp = hpMax;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Monster(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.position = new Vector2((float)Math.random()*1040, 640 -(float)Math.random()*640);
        this.dst = new Vector2(position);
        this.tmp = new Vector2(0, 0);
        this.speed = 100.0f;
        this.hpMax = 30;
        this.hp = 30;
    }

    public boolean takeDamage(int amount) {
        hp -= amount;
        if(hp == 0){
            return true;
        }
        return  false;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void update(float dt) {
        lifetime += dt;
        atackTime += dt;
        if(position.dst(gameScreen.getHero().getPosition()) < 24){
            if(atackTime > 0.5f){
                atackTime = 0;
                gameScreen.getHero().heroDamage(1);
                if(gameScreen.getHero().getHp() == 0){
                    gameScreen.getHero().setHP();
                    setPosition();
                }
            }
        }

        tmp.set(gameScreen.getHero().getPosition()).sub(position).nor().scl(speed);
        position.mulAdd(tmp, dt);
    }
}

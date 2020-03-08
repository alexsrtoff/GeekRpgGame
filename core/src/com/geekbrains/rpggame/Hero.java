package com.geekbrains.rpggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Hero {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private TextureRegion texturePointer;
    private TextureRegion textureHp;
    private Vector2 position;
    private Vector2 dst;
    private Vector2 tmp;
    private float lifetime;
    private float speed;
    private int hp;
    private int hpMax;
    private StringBuilder strBuilder;
    private int coins;
    private int maxCoins;
    private int attempt;

    public Vector2 getPosition() {
        return position;
    }

    public int getHp() {
        return hp;
    }

    public void setHP(){
        hp = hpMax;
        checkCoins();
        attempt += 1;
        coins = 0;
    }

    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.texturePointer = Assets.getInstance().getAtlas().findRegion("pointer");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.position = new Vector2(100, 100);
        this.dst = new Vector2(position);
        this.tmp = new Vector2(0, 0);
        this.speed = 300.0f;
        this.hpMax = 10;
        this.hp = 10;
        this.strBuilder = new StringBuilder();
        this.coins = 0;
        this.maxCoins = 0;
        this.attempt = 1;
    }

    public void heroDamage(int damage) {
        hp -= damage;
    }

    public void setCoins(int coins) {
       this.coins += coins;
    }

    public void checkCoins(){
        if(coins > maxCoins) maxCoins = coins;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texturePointer, dst.x - 30, dst.y - 30, 30, 30, 60, 60, 0.5f, 0.5f, lifetime * 90.0f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void renderGUI(SpriteBatch batch, BitmapFont font) {
        strBuilder.setLength(0);
        strBuilder.append("Class: ").append("Knight").append("\n");
        strBuilder.append("HP: ").append(hp).append(" / ").append(hpMax).append("\n");
        strBuilder.append("Coins: ").append(coins).append("\n");
        strBuilder.append("Max Coins: ").append(maxCoins).append("\n");
        strBuilder.append("Attempts: ").append(attempt).append("\n");
        font.draw(batch, strBuilder, 10, 630);
    }

    public void update(float dt) {
        lifetime += dt;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            dst.set(Gdx.input.getX(), 640.0f - Gdx.input.getY());
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            gameScreen.getProjectilesController().setup(position.x, position.y, Gdx.input.getX(), 640.0f - Gdx.input.getY());
        }
        tmp.set(dst).sub(position).nor().scl(speed); // вектор скорости
        if (position.dst(dst) > speed * dt) {
            position.mulAdd(tmp, dt);
        } else {
            position.set(dst);
        }
    }
}
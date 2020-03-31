package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.geekbrains.rpg.game.logic.utils.Poolable;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Monster extends GameCharacter implements Poolable {
    @Override
    public boolean isActive() {
        return hp > 0;
    }

    public Monster(GameController gc) {
        super(gc, 20, 80.0f);
        this.textures = new TextureRegion(Assets.getInstance().getAtlas().findRegion("dwarf")).split(60, 60);
        this.changePosition(800.0f, 300.0f);
        this.dst.set(this.position);
        this.visionRadius = 160.0f;
        this.weapon = gc.getWeaponsController().getOneFromAnyPrototype();
    }

    public void generateMe() {
        do {
            changePosition(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        } while (!gc.getMap().isGroundPassable(position));
        hpMax = 80;
        hp = hpMax;
    }

    @Override
    public void onDeath() {
        super.onDeath();
        gc.getWeaponsController().setup(position.x, position.y);
        gc.getPowerUpsController().setup(position.x, position.y);
    }

    @Override
    public boolean takeDamage(GameCharacter attacker, int amount) {
        gc.getInfoController().setupAnyAmount(position.x, position.y, Color.WHITE, "-", amount);
        return super.takeDamage(attacker, amount);
    }

    public void update(float dt) {
        super.update(dt);
        stateTimer -= dt;
        if (stateTimer < 0.0f) {
            if (state == State.ATTACK) {
                target = null;
            }
            state = State.values()[MathUtils.random(0, 1)];
            if (state == State.MOVE) {
                dst.set(MathUtils.random(1280), MathUtils.random(720));
            }
            stateTimer = MathUtils.random(2.0f, 5.0f);
        }
        if (state != State.RETREAT && this.position.dst(gc.getHero().getPosition()) < visionRadius) {
            state = State.ATTACK;
            target = gc.getHero();
            stateTimer = 10.0f;
        }
        if (hp < hpMax * 0.2 && state != State.RETREAT) {
            state = State.RETREAT;
            stateTimer = 1.0f;
            dst.set(position.x + MathUtils.random(100, 200) * Math.signum(position.x - lastAttacker.position.x),
                    position.y + MathUtils.random(100, 200) * Math.signum(position.y - lastAttacker.position.y));
        }
    }
}

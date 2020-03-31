package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Hero extends GameCharacter {
    private StringBuilder strBuilder;
    private Sound sound;

    public Hero(GameController gc) {
        super(gc, 500, 120.0f);
        this.textures = new TextureRegion(Assets.getInstance().getAtlas().findRegion("knight")).split(60, 60);
        this.changePosition(100.0f, 100.0f);
        this.dst.set(position);
        this.strBuilder = new StringBuilder();
        this.weapon = gc.getWeaponsController().getOneFromAnyPrototype();
        this.sound = Gdx.audio.newSound(Gdx.files.internal("audio/explosion.wav"));
    }

    public void renderGUI(SpriteBatch batch, BitmapFont font) {
        strBuilder.setLength(0);
        strBuilder.append("Class: ").append("Knight").append("\n");
        strBuilder.append("HP: ").append(hp).append(" / ").append(hpMax).append("\n");
        strBuilder.append("Coins: ").append(coins).append("\n");
        strBuilder.append("Weapon: ").append(weapon.getTitle()).append(" [").append(weapon.getMinDamage() + levelPower).append("-").append(weapon.getMaxDamage() + levelPower).append("]\n");
        strBuilder.append("Experience: ").append(exp).append("/").append(nextLevelExp).append("\n");
        font.draw(batch, strBuilder, 10, 710);
    }

    @Override
    public void onDeath() {
        super.onDeath();
        coins = 0;
        hp = hpMax;
    }

    @Override
    public boolean takeDamage(GameCharacter attacker, int amount) {
//         sound.play();
        gc.getInfoController().setupAnyAmount(position.x, position.y, Color.RED, "-", amount);
        return super.takeDamage(attacker, amount);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            for (int i = 0; i < gc.getMonstersController().getActiveList().size(); i++) {
                Monster m = gc.getMonstersController().getActiveList().get(i);
                if (m.getPosition().dst(gc.getMouse()) < 30.0f) {
                    state = State.ATTACK;
                    target = m;
                    return;
                }
            }
            dst.set(gc.getMouse());
            state = State.MOVE;
            target = null;
        }
    }

    public void dispose() {
        sound.dispose();
    }
}
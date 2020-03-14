package com.geekbrains.rpg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geekbrains.rpg.game.screens.ScreenManager;

public class GeekRpgGame extends Game {
    private SpriteBatch batch;

    // Домашнее задание:
    // - Разбор кода и пишите какие вопросы возникли
    // - Добавить класс Weapon (оружие) и раздать каждому персонажу по оружию
    // Оружие определяет тип бойца - ближний/дальний бой. Свойства оружия:
    // дальность атаки, урон, скорость атаки
    // - * Из монстров может выпадать оружие (рисуем картинку оружия), которое
    // подбирается как монстрами, так и героем, заменяя имеющееся

    @Override
    public void create() {
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this, batch);
        ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
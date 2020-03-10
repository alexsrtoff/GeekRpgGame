package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class MonsterController extends ObjectPool<Monster> {
    GameController gc;
    private float addMonster;

    public MonsterController(GameController gc){
        this.gc = gc;
        setup();
    }

    @Override
    protected Monster newObject() {
        return new Monster(gc);
    }


    public void setup() {
        getActiveElement().setup();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).render(batch, null);
        }
    }

    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
        addMonster += dt;
        if(addMonster >= 30) {
            addMonster = 0;
            setup();
        }
    }


}

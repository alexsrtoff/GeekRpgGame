//package com.geekbrains.rpg.game.logic;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.geekbrains.rpg.game.logic.utils.ObjectPool;
//
//public class ItemsController extends ObjectPool<Item> {
//
//    private GameController gc;
//
//    public ItemsController(GameController gc) {
//        this.gc = gc;
//    }
//
//    @Override
//    protected Item newObject() {
//        return new Item();
//    }
//
//    public void setup(float x, float y) {
//        Item item = getActiveElement();
//        Item.Type [] types = Item.Type.values();
//        Item.Type type = types[MathUtils.random(0,1)];
//        int volume = 0;
//        if(type == Item.Type.COIN){
//            volume = MathUtils.random(1,10);
//        }else volume = 5;
//        item.setup(type, volume);
//        item.setPosition(x, y);
//    }
//
//    public void update(float dt) {
//        checkPool();
//    }
//
//
//}

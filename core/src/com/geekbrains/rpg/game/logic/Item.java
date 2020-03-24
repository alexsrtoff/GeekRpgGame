//package com.geekbrains.rpg.game.logic;
//
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Vector2;
//import com.geekbrains.rpg.game.logic.utils.MapElement;
//import com.geekbrains.rpg.game.logic.utils.Poolable;
//import com.geekbrains.rpg.game.screens.utils.Assets;
//
//public class Item implements MapElement, Poolable {
//
//
//
//
//    public enum Type {
//        COIN, HEALS
//    }
//
//    GameController gc;
//
//    private TextureRegion texture;
//    private Type type;
//    private boolean active;
//    private Vector2 position;
//    private int coin;
//    private int heals;
//    private int volume;
//
//    public Vector2 getPosition() {
//        return position;
//    }
//
//    public void setPosition(float x, float y) {
//        this.position.set(x, y);
//    }
//
//
//    public void consume(GameController gc) {
//        if(type == Type.COIN){
//            gc.getHero().addCoins(volume);
//            active = false;
//        }else {
//            gc.getHero().addHealth(volume);
//            active = false;
//        }
//}
//
//    @Override
//    public void render(SpriteBatch batch, BitmapFont font) {
//        batch.draw(texture, position.x - 32, position.y - 32);
//    }
//
//    @Override
//    public int getCellX() {
//        return (int) position.x / 80;
//    }
//
//    @Override
//    public int getCellY() {
//        return  (int) position.y / 80;
//    }
//
//    @Override
//    public float getY() {
//        return 0;
//    }
//
//    @Override
//    public boolean isActive() {
//        return active;
//    }
//
//
//    public Item() {
//        this.position = new Vector2(0, 0);
//    }
//// запутался в текстурах, вмсто бутылки и монеты у меня выпадают рыцарь и поинтер.
//// добавить новые текстуры не получилось. Объясните по возможности. Спасибо!
//    public void setup(Type type, int volume){
//
//        this.type = type;
//        this.volume = volume;
//        if(type == Type.COIN){
//            texture = Assets.getInstance().getAtlas().findRegion("knight");
//        }else texture = Assets.getInstance().getAtlas().findRegion("pointer");
//        this.active = true;
//    }
//}

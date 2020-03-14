package com.geekbrains.rpg.game.logic;



public class Weapon {



    private int damage;
    private float range;
    private float atackSpeed;

    public Weapon( int damage, float range, float atackSpeed) {
        this.damage = damage;
        this.range = range;
        this.atackSpeed = atackSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getAtackSpeed() {
        return atackSpeed;
    }

}

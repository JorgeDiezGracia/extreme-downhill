package com.svalero.extremedownhill.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class NPC {

    public float x, y;
    public float width, height;
    public boolean active = true;

    public NPC(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update(float delta, Player player);

    public abstract void render(ShapeRenderer shapeRenderer);

    public boolean collidesWith(Player player) {
        return active &&
            player.x < x + width &&
            player.x + player.width > x &&
            player.y < y + height &&
            player.y + player.height > y;
    }
}

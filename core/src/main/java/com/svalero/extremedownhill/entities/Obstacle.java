package com.svalero.extremedownhill.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Obstacle {

    public float x, y;
    public float width, height;
    public boolean active = true;

    public Obstacle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1); // gris (roca)
        shapeRenderer.rect(x, y, width, height);
    }

    public boolean collidesWith(Player player) {
        return active &&
            player.x < x + width &&
            player.x + player.width > x &&
            player.y < y + height &&
            player.y + player.height > y;
    }
}

package com.svalero.extremedownhill.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Boar extends NPC {

    private float speed;

    public Boar(float x, float y) {
        super(x, y, 50, 40);
        this.speed = 200f;
    }

    @Override
    public void update(float delta, Player player) {
        // El jabalí corre hacia el jugador
        if (player.x > x) {
            x += speed * delta;
        } else {
            x -= speed * delta;
        }
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        // Cuerpo marrón oscuro
        shapeRenderer.setColor(0.4f, 0.2f, 0.1f, 1);
        shapeRenderer.rect(x, y, width, height);
        // Cabeza
        shapeRenderer.setColor(0.3f, 0.15f, 0.05f, 1);
        shapeRenderer.rect(x + width - 15, y + 10, 20, 25);
    }
}

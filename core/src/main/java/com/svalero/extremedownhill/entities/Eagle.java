package com.svalero.extremedownhill.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Eagle extends NPC {

    private float speed = 250f;
    private float startY;
    private float time = 0f;

    public Eagle(float x, float y) {
        super(x, y, 40, 30);
        this.startY = y;
    }

    @Override
    public void update(float delta, Player player) {
        // El águila vuela en diagonal hacia el jugador
        time += delta;
        y = startY + (float) Math.sin(time * 2) * 60f;

        if (player.x > x) {
            x += speed * delta;
        } else {
            x -= speed * delta;
        }
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        // Cuerpo marrón
        shapeRenderer.setColor(0.6f, 0.3f, 0f, 1);
        shapeRenderer.rect(x, y, width, height);
        // Alas
        shapeRenderer.setColor(0.4f, 0.2f, 0f, 1);
        shapeRenderer.rect(x - 20, y + 10, 20, 10);
        shapeRenderer.rect(x + width, y + 10, 20, 10);
    }
}

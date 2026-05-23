package com.svalero.extremedownhill.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Snake extends NPC {

    private float time = 0f;
    private float startY;

    public Snake(float x, float y) {
        super(x, y, 60, 20);
        this.startY = y;
    }

    @Override
    public void update(float delta, Player player) {
        // La serpiente es estática pero ondula
        time += delta;
        y = startY + (float) (Math.sin(time * 3) * 5);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        // Cuerpo verde
        shapeRenderer.setColor(0.1f, 0.6f, 0.1f, 1);
        shapeRenderer.rect(x, y, width, height);
        // Cabeza
        shapeRenderer.setColor(0.1f, 0.4f, 0.1f, 1);
        shapeRenderer.rect(x + width - 10, y, 20, 20);
        // Lengua
        shapeRenderer.setColor(1f, 0f, 0f, 1);
        shapeRenderer.rect(x + width + 10, y + 8, 10, 4);
    }
}

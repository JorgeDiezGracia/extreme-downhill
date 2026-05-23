package com.svalero.extremedownhill.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    // Posición y tamaño
    public float x, y;
    public float width = 50, height = 50;

    // Movimiento
    private float speed;
    private float minSpeed = 100f;
    private float maxSpeed = 600f;
    private float acceleration = 150f;

    // Salto
    private float velocityY = 0f;
    private boolean onGround = true;
    private static final float GRAVITY = -800f;
    private static final float JUMP_FORCE = 500f;
    public float groundY;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.groundY = y;
        this.speed = 200f;
    }

    public void update(float delta) {
        // Acelerar con flecha derecha
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            speed = Math.min(speed + acceleration * delta, maxSpeed);
        }
        // Frenar con flecha izquierda
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            speed = Math.max(speed - acceleration * delta, minSpeed);
        }
        // Saltar con espacio o flecha arriba
        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) && onGround) {
            velocityY = JUMP_FORCE;
            onGround = false;
        }

        // Gravedad
        velocityY += GRAVITY * delta;
        y += velocityY * delta;

        // Suelo
        if (y <= groundY) {
            y = groundY;
            velocityY = 0;
            onGround = true;
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0.5f, 0, 1); // naranja
        shapeRenderer.rect(x, y, width, height);
    }

    public float getSpeed() {
        return speed;
    }
}

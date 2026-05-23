package com.svalero.extremedownhill.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera hudCamera;

    public HUD(SpriteBatch batch) {
        this.batch = batch;
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale(1.5f);

        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, 800, 480);
    }

    public void render(int lives, float speed, float distance) {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        font.draw(batch, "Vidas: " + lives, 20, 460);
        font.draw(batch, "Velocidad: " + (int) speed + " km/h", 20, 435);
        font.draw(batch, "Distancia: " + (int) (distance / 100) + " m", 20, 410);

        batch.end();
    }

    public void dispose() {
        font.dispose();
    }
}

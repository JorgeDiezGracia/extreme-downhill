package com.svalero.extremedownhill.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.svalero.extremedownhill.ExtremeDownhill;

public class MenuScreen implements Screen {

    private final ExtremeDownhill game;
    private SpriteBatch batch;

    public MenuScreen(ExtremeDownhill game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1); // fondo oscuro
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Aquí dibujaremos el menú más adelante
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}

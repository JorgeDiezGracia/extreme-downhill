package com.svalero.extremedownhill.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.svalero.extremedownhill.ExtremeDownhill;

public class GameScreen implements Screen {

    private final ExtremeDownhill game;
    private SpriteBatch batch;

    public GameScreen(ExtremeDownhill game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.6f, 0.2f, 1); // fondo verde bosque
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

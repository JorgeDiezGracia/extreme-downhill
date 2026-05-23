package com.svalero.extremedownhill.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.svalero.extremedownhill.ExtremeDownhill;
import com.svalero.extremedownhill.entities.Player;

public class GameScreen implements Screen {

    private final ExtremeDownhill game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Player player;
    private OrthographicCamera camera;

    // Suelo
    private static final float GROUND_Y = 80f;
    private static final float GROUND_HEIGHT = 80f;

    // Distancia recorrida
    private float distance = 0f;

    public GameScreen(ExtremeDownhill game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        // Cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Jugador encima del suelo
        this.player = new Player(100, GROUND_Y + GROUND_HEIGHT);
        player.groundY = GROUND_Y + GROUND_HEIGHT;
    }

    @Override
    public void render(float delta) {
        // Actualizar distancia
        distance += player.getSpeed() * delta;

        // Mover cámara con el jugador
        camera.position.x = player.x + 300;
        camera.update();

        // Limpiar pantalla
        Gdx.gl.glClearColor(0.4f, 0.7f, 1f, 1); // cielo azul
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar jugador
        player.update(delta);
        player.x += player.getSpeed() * delta;

        // Dibujar
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Suelo marrón
        shapeRenderer.setColor(0.5f, 0.3f, 0.1f, 1);
        shapeRenderer.rect(camera.position.x - 500, 0, 1000, GROUND_HEIGHT);

        // Hierba verde
        shapeRenderer.setColor(0.2f, 0.6f, 0.2f, 1);
        shapeRenderer.rect(camera.position.x - 500, GROUND_HEIGHT, 1000, 20);

        // Jugador
        player.render(shapeRenderer);

        shapeRenderer.end();
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}

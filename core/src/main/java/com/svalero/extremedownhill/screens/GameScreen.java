package com.svalero.extremedownhill.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.svalero.extremedownhill.ExtremeDownhill;
import com.svalero.extremedownhill.entities.Obstacle;
import com.svalero.extremedownhill.entities.Player;
import com.svalero.extremedownhill.ui.HUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen {

    private final ExtremeDownhill game;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private final Player player;
    private final OrthographicCamera camera;
    private HUD hud;

    // Suelo
    private static final float GROUND_HEIGHT = 80f;

    // Obstáculos
    private final List<Obstacle> obstacles = new ArrayList<>();
    private final Random random = new Random();
    private float nextObstacleX;

    // Distancia recorrida
    private float distance = 0f;

    // Vidas
    private int lives = 3;
    private float invincibleTimer = 0f;

    public GameScreen(ExtremeDownhill game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        this.player = new Player(100, GROUND_HEIGHT + 80);
        player.groundY = GROUND_HEIGHT + 80;

        nextObstacleX = 600f;

        hud = new HUD(batch);
    }

    private void spawnObstacle() {
        float width = 30 + random.nextFloat() * 30;
        float height = 30 + random.nextFloat() * 40;
        obstacles.add(new Obstacle(nextObstacleX, GROUND_HEIGHT + 80, width, height));
        nextObstacleX += 300 + random.nextFloat() * 400;
    }

    @Override
    public void render(float delta) {
        // Timers
        if (invincibleTimer > 0) invincibleTimer -= delta;

        // Actualizar jugador
        player.update(delta);
        player.x += player.getSpeed() * delta;
        distance += player.getSpeed() * delta;

        // Generar obstáculos
        if (player.x + 800 > nextObstacleX) {
            spawnObstacle();
        }

        // Colisiones
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(player) && invincibleTimer <= 0) {
                lives--;
                invincibleTimer = 2f;
                if (lives <= 0) {
                    game.setScreen(new MenuScreen(game));
                    return;
                }
            }
        }

        // Eliminar obstáculos fuera de pantalla
        obstacles.removeIf(o -> o.x < player.x - 600);

        // Cámara
        camera.position.x = player.x + 300;
        camera.update();

        // Limpiar pantalla
        Gdx.gl.glClearColor(0.4f, 0.7f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Suelo
        shapeRenderer.setColor(0.5f, 0.3f, 0.1f, 1);
        shapeRenderer.rect(camera.position.x - 500, 0, 1000, GROUND_HEIGHT);

        // Hierba
        shapeRenderer.setColor(0.2f, 0.6f, 0.2f, 1);
        shapeRenderer.rect(camera.position.x - 500, GROUND_HEIGHT, 1000, 20);

        // Obstáculos
        for (Obstacle obstacle : obstacles) {
            obstacle.render(shapeRenderer);
        }

        // Jugador (parpadea si es invencible)
        if (invincibleTimer <= 0 || (int)(invincibleTimer * 10) % 2 == 0) {
            player.render(shapeRenderer);
        }

        shapeRenderer.end();

        // HUD
        hud.render(lives, player.getSpeed(), distance);
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
        hud.dispose();
    }
}

package com.svalero.extremedownhill.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.svalero.extremedownhill.ExtremeDownhill;

public class MenuScreen implements Screen {

    private final ExtremeDownhill game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final BitmapFont titleFont;
    private final OrthographicCamera camera;

    // Opciones del menú
    private static final int OPTION_PLAY = 0;
    private static final int OPTION_INSTRUCTIONS = 1;
    private static final int OPTION_CONFIG = 2;
    private static final int OPTION_EXIT = 3;
    private int selectedOption = 0;

    // Estado
    private boolean showInstructions = false;
    private boolean showConfig = false;

    // Configuración
    private boolean soundEnabled = true;
    private int difficulty = 1; // 0=fácil, 1=normal, 2=difícil

    public MenuScreen(ExtremeDownhill game) {
        this.game = game;
        this.batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        font = new BitmapFont();
        font.getData().setScale(1.5f);

        titleFont = new BitmapFont();
        titleFont.getData().setScale(3f);
        titleFont.setColor(Color.YELLOW);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (showInstructions) {
            renderInstructions();
        } else if (showConfig) {
            renderConfig();
        } else {
            renderMenu();
        }

        batch.end();

        handleInput();
    }

    private void renderMenu() {
        // Título
        titleFont.draw(batch, "EXTREME DOWNHILL", 120, 420);

        // Opciones
        drawOption("Jugar", OPTION_PLAY, 350);
        drawOption("Instrucciones", OPTION_INSTRUCTIONS, 310);
        drawOption("Configuracion", OPTION_CONFIG, 270);
        drawOption("Salir", OPTION_EXIT, 230);

        // Indicación
        font.setColor(Color.GRAY);
        font.draw(batch, "Usa flechas arriba/abajo y Enter para seleccionar", 100, 80);
    }

    private void drawOption(String text, int option, float y) {
        if (selectedOption == option) {
            font.setColor(Color.YELLOW);
            font.draw(batch, "> " + text, 320, y);
        } else {
            font.setColor(Color.WHITE);
            font.draw(batch, text, 330, y);
        }
    }

    private void renderInstructions() {
        titleFont.setColor(Color.YELLOW);
        titleFont.draw(batch, "INSTRUCCIONES", 180, 430);

        font.setColor(Color.WHITE);
        font.draw(batch, "-> / D : Acelerar", 200, 350);
        font.draw(batch, "<- / A : Frenar", 200, 310);
        font.draw(batch, "Espacio / Arriba : Saltar", 200, 270);
        font.draw(batch, "Esquiva obstaculos y NPCs", 200, 230);
        font.draw(batch, "Tienes 3 vidas", 200, 190);

        font.setColor(Color.GRAY);
        font.draw(batch, "Pulsa Escape para volver", 250, 80);
    }

    private void renderConfig() {
        titleFont.setColor(Color.YELLOW);
        titleFont.draw(batch, "CONFIGURACION", 190, 430);

        font.setColor(Color.WHITE);
        font.draw(batch, "Sonido: " + (soundEnabled ? "ON" : "OFF"), 300, 320);
        font.draw(batch, "Dificultad: " + getDifficultyText(), 300, 270);

        font.setColor(Color.GRAY);
        font.draw(batch, "S = Sonido  |  D = Dificultad  |  Escape = Volver", 100, 80);
    }

    private String getDifficultyText() {
        switch (difficulty) {
            case 0: return "Facil";
            case 2: return "Dificil";
            default: return "Normal";
        }
    }

    private void handleInput() {
        if (showInstructions || showConfig) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                showInstructions = false;
                showConfig = false;
            }
            if (showConfig) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.S)) soundEnabled = !soundEnabled;
                if (Gdx.input.isKeyJustPressed(Input.Keys.D)) difficulty = (difficulty + 1) % 3;
            }
            return;
        }

        // Navegar menú
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + 4) % 4;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % 4;
        }

        // Seleccionar opción
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selectedOption) {
                case OPTION_PLAY:
                    game.setScreen(new GameScreen(game));
                    break;
                case OPTION_INSTRUCTIONS:
                    showInstructions = true;
                    break;
                case OPTION_CONFIG:
                    showConfig = true;
                    break;
                case OPTION_EXIT:
                    Gdx.app.exit();
                    break;
            }
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        titleFont.dispose();
    }
}

package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class MainGameOverScreen implements Screen {

    public OrthographicCamera camera = new OrthographicCamera();
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 100;

    private static final int X_POS_BUTTON = 250;
    private static final int Y_POS_GAMEOVER = 250;
    private static final int Y_POS_GAMEBUTTON = 150;
    private static final int Y_POS_EXITBUTTON = 50;

    public Robo game;
    Texture gameButton;
    Texture exitButton;
    Texture gameOverButton;
    int middleOfScreen;

    public MainGameOverScreen(Robo robo) {
        this.game = robo;
        resize(700, 500);
        gameButton = new Texture("assets/buttons/NextgameBtn.png");
        exitButton = new Texture("assets/buttons/ExitBtn.png");
        gameOverButton = new Texture("assets/gameover.png");
        middleOfScreen = Gdx.graphics.getWidth()/2;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();


        // My pitiful attempt at fixing the coordinate system
        // camera.setToOrtho(false, 500, 500);
        if (Gdx.input.getY() > 500 - Y_POS_GAMEBUTTON - BUTTON_HEIGHT && Gdx.input.getY() < 500 - Y_POS_GAMEBUTTON &&
                Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getX() < X_POS_BUTTON + BUTTON_WIDTH) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));

            }
        }
        if (Gdx.input.getY() > 500 - Y_POS_EXITBUTTON - BUTTON_HEIGHT && Gdx.input.getY() < 500 - Y_POS_EXITBUTTON &&
                Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getX() < X_POS_BUTTON + BUTTON_WIDTH) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                Gdx.app.exit();
            }
        }

        game.batch.draw(gameOverButton, middleOfScreen - 300, Y_POS_GAMEOVER, 600, 125);
        game.batch.draw(gameButton, middleOfScreen - BUTTON_WIDTH/2, Y_POS_GAMEBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(exitButton, middleOfScreen - BUTTON_WIDTH/2, Y_POS_EXITBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        int screenWidth = width;
        int screenHeight = height;
        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
        game.batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


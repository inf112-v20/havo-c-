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
    //private static final int Y_POS_VICTORY = 400;
    private static final int Y_POS_GAMEBUTTON = 250;
    private static final int Y_POS_EXITBUTTON = 100;
    //bruh

    public Robo game;
    Texture gameButton;
    Texture exitButton;

    public MainGameOverScreen(Robo robo) {
        this.game = robo;
        gameButton = new Texture("assets/NextgameBtn.png");
        exitButton = new Texture("assets/ExitBtn.png");
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
            if (Gdx.input.isTouched()) {
                this.dispose();
                //game.setScreen(new MainGameScreen(game));

            }
        }
        if (Gdx.input.getY() > 500 - Y_POS_EXITBUTTON - BUTTON_HEIGHT && Gdx.input.getY() < 500 - Y_POS_EXITBUTTON &&
                Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getX() < X_POS_BUTTON + BUTTON_WIDTH) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                Gdx.app.exit();
            }
        }
        game.batch.draw(gameButton, X_POS_BUTTON, Y_POS_GAMEBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(exitButton, X_POS_BUTTON, Y_POS_EXITBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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


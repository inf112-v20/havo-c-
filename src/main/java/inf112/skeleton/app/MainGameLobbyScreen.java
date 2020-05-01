package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class MainGameLobbyScreen implements Screen {

    public OrthographicCamera camera = new OrthographicCamera();
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 100;

    private static final int X_POS_BUTTON = 250;
    private static final int Y_POS_STARTBUTTON = 250;
    private static final int Y_POS_EXITBUTTON = 100;


    public Robo game;
    Texture playButton;
    Texture backButton;
    public MainGameLobbyScreen (Robo robo) {
        this.game = robo;
        playButton = new Texture("assets/buttons/startKnapp.png");
        backButton = new Texture("assets/buttons/BackBtn.png");
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
        if (Gdx.input.getY()  > 500 - Y_POS_STARTBUTTON - BUTTON_HEIGHT && Gdx.input.getY() < 500 - Y_POS_STARTBUTTON &&
                Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getX() < X_POS_BUTTON + BUTTON_WIDTH) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));

            }
        }
        if (Gdx.input.getY() > 500 - Y_POS_EXITBUTTON - BUTTON_HEIGHT && Gdx.input.getY() < 500 - Y_POS_EXITBUTTON &&
                Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getX() < X_POS_BUTTON + BUTTON_WIDTH) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        game.batch.draw(playButton, X_POS_BUTTON, Y_POS_STARTBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(backButton, X_POS_BUTTON,Y_POS_EXITBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
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
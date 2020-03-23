package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {


    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 100;

    private static final int X_POS_BUTTON = 125;
    private static final int Y_POS_STARTBUTTON = 250;
    private static final int Y_POS_EXITBUTTON = 100;


    public Robo game;
    Texture playButton;
    Texture exitButton;
    public MainMenuScreen (Robo robo) {
        this.game = robo;
        playButton = new Texture("assets/startbuttonV2.png");
        exitButton = new Texture("assets/ExitBtn.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(2, 2, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();



// I don't get the brain power for this
        if (Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getY() < Y_POS_STARTBUTTON){
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));

            }}
        if (Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getY() > Y_POS_EXITBUTTON) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                Gdx.app.exit();

            }}
        game.batch.draw(playButton, X_POS_BUTTON, Y_POS_STARTBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(exitButton, X_POS_BUTTON,Y_POS_EXITBUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
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
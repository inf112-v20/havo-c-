package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    public Robo game;
    Texture playButton;
    Texture exitButton;
    public MainMenuScreen (Robo robo) {
        this.game = robo;
        playButton = new Texture("assets/play_button.png");
        exitButton = new Texture("assets/exit_button.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(2, 2, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();



        if (Gdx.input.getX() > 100 && Gdx.input.getY() > 100) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
                //Gdx.app.exit();
            }}
        game.batch.draw(exitButton, 100,100);
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
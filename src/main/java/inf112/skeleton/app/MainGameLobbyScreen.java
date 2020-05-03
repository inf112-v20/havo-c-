package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;

public class MainGameLobbyScreen implements Screen {

    public OrthographicCamera camera = new OrthographicCamera();

    private static final int SCREEN_HEIGHT = 500;
    private static final int SCREEN_WIDTH = 500;

    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 100;

    private static final int X_POS_BUTTON = 250;
    private static final int Y_POS_STARTBUTTON = 250;
    private static final int Y_POS_EXITBUTTON = 100;

    private int pictureScalling = 1;

    // Coordinates for arrows
    private static final int X_PLACEMENT_ARROW = 32;
    private static final int Y_PLACEMENT_ARROW = 265;
    private static final int DISTANCE_BETWEEN_ARROW = 92;

    // Size of textures
    private static final int ARROW_WIDTH = 80;
    private static final int ARROW_HEIGHT = 45;
    private static final int MENU_LINES_WIDTH = 215;
    private static final int MENU_LINES_HEIGHT = 350;


    // Coordinates for menuLines
    private static final int X_PLACEMENT_MENU_LINES = 10;
    private static final int Y_PLACEMENT_MENU_LINES = 40;
    private static final int DISTANCE_BETWEEN_MENU_LINES = 215;



    public Robo game;
    Texture playButton;
    Texture backButton;
    Texture greenArrow;
    Texture backArrow;
    Texture menuLines;

    private BitmapFont font;

    private int numberOfEnemies = 1;

    public MainGameLobbyScreen (Robo robo) {
        this.game = robo;
        playButton = new Texture("assets/buttons/startKnapp.png");
        backButton = new Texture("assets/buttons/BackButton.png");
        greenArrow = new Texture("assets/gameLobby/greenArrow.png");
        backArrow = new Texture("assets/gameLobby/backArrow.png");
        menuLines = new Texture("assets/gameLobby/menyMal.png");

        font = new BitmapFont();
        font.setColor(Color.MAGENTA);


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
                drawNumberOfEnemeies(true);

            }
        }
        if (Gdx.input.getY() > 500 - Y_POS_EXITBUTTON - BUTTON_HEIGHT && Gdx.input.getY() < 500 - Y_POS_EXITBUTTON &&
                Gdx.input.getX() > X_POS_BUTTON && Gdx.input.getX() < X_POS_BUTTON + BUTTON_WIDTH) {
            if (Gdx.input.isTouched()) {
                drawNumberOfEnemeies(false);
            }
        }
        drawTexture();
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

    private void drawTexture() {
        // Draws the arrows
        game.batch.draw(greenArrow, X_PLACEMENT_ARROW + DISTANCE_BETWEEN_ARROW, Y_PLACEMENT_ARROW, ARROW_WIDTH, ARROW_HEIGHT);
        game.batch.draw(backArrow, X_PLACEMENT_ARROW ,Y_PLACEMENT_ARROW, ARROW_WIDTH, ARROW_HEIGHT);
        game.batch.draw(greenArrow, X_PLACEMENT_ARROW + DISTANCE_BETWEEN_ARROW + DISTANCE_BETWEEN_MENU_LINES + 10,Y_PLACEMENT_ARROW, ARROW_WIDTH, ARROW_HEIGHT);
        game.batch.draw(backArrow, X_PLACEMENT_ARROW + DISTANCE_BETWEEN_MENU_LINES + 10,Y_PLACEMENT_ARROW, ARROW_WIDTH, ARROW_HEIGHT);

        // Draws the menuLines
        game.batch.draw(menuLines, X_PLACEMENT_MENU_LINES,Y_PLACEMENT_MENU_LINES, MENU_LINES_WIDTH, MENU_LINES_HEIGHT);
        game.batch.draw(menuLines, X_PLACEMENT_MENU_LINES + DISTANCE_BETWEEN_MENU_LINES + 10,Y_PLACEMENT_MENU_LINES, MENU_LINES_WIDTH, MENU_LINES_HEIGHT);

        // Draws the start game and back options
        game.batch.draw(playButton, X_PLACEMENT_MENU_LINES * 2 + DISTANCE_BETWEEN_MENU_LINES * 2 + 10, Y_PLACEMENT_MENU_LINES + 9, MENU_LINES_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(backButton, X_PLACEMENT_MENU_LINES * 2 + DISTANCE_BETWEEN_MENU_LINES * 2 + 10,Y_PLACEMENT_MENU_LINES + 9 + BUTTON_HEIGHT + 10, MENU_LINES_WIDTH, BUTTON_HEIGHT);

    }
    private void drawNumberOfEnemeies(Boolean addSub) {
        setFontSettings();
        if(addSub) {
            numberOfEnemies++;
        } else {
            numberOfEnemies--;
        }

        font.draw(game.batch, Integer.toString(numberOfEnemies), X_POS_BUTTON, Y_POS_STARTBUTTON);

    }

    private void setFontSettings() {
        font.getData().setScale(5);
    }
}

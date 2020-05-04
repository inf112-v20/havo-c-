package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;

public class MainGameLobbyScreen implements Screen {

    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 100;



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
    private Texture playButton;
    private Texture backButton;
    private Texture greenArrow;
    private Texture backArrow;
    private Texture menuLines;
    private Texture one;
    private Texture two;
    private Texture three;
    private Texture four;
    private Texture five;
    private Texture clusterCross;
    private Texture dizzyHighway;
    private Texture testingGround;
    private Texture pickMap;
    private Texture pickEnemies;
    private Texture gameLobby;

    private ArrayList<Texture> numbers = new ArrayList<>();
    private ArrayList<Texture> maps = new ArrayList<>();

    private int numberOfEnemies = 0;
    private int maxNumberOfEnemies = 5;
    private int minNumberOfEnemies = 1;

    private int mapNumber = 0;
    private int maxMapNumber = 3;
    private int minMapNumber = 1;

    private BitmapFont font;



    public MainGameLobbyScreen (Robo robo) {
        this.game = robo;
        playButton = new Texture("assets/buttons/startKnapp.png");
        backButton = new Texture("assets/buttons/BackButton.png");
        greenArrow = new Texture("assets/gameLobby/greenArrow.png");
        backArrow = new Texture("assets/gameLobby/backArrow.png");
        menuLines = new Texture("assets/gameLobby/menyMal.png");

        fillTextTexture();
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


        // Input for enemies
        if (Gdx.input.getY()  > 190 && Gdx.input.getY() < 235 &&
                Gdx.input.getX() > X_PLACEMENT_ARROW && Gdx.input.getX() < X_PLACEMENT_ARROW + DISTANCE_BETWEEN_ARROW + ARROW_WIDTH) {
            if (Gdx.input.justTouched()) {
                numberOfEnemies(Gdx.input.getX());

            }
        }
        // Input for maps
        if (Gdx.input.getY() > 190  && Gdx.input.getY() < 235 &&
                Gdx.input.getX() > X_PLACEMENT_ARROW + DISTANCE_BETWEEN_MENU_LINES + 10 && Gdx.input.getX() < X_PLACEMENT_ARROW + ARROW_WIDTH
                + DISTANCE_BETWEEN_MENU_LINES + 10 + DISTANCE_BETWEEN_ARROW) {
            if (Gdx.input.justTouched()) {
                pickAMap(Gdx.input.getX());
            }
        }

        // Input for start Game/back
        if (Gdx.input.getY()  > 240 && Gdx.input.getY() < 450 &&
                Gdx.input.getX() > X_PLACEMENT_MENU_LINES * 2 + DISTANCE_BETWEEN_MENU_LINES * 2 + 10 && Gdx.input.getX() < X_PLACEMENT_MENU_LINES * 2 + DISTANCE_BETWEEN_MENU_LINES * 2 + 10 + BUTTON_WIDTH) {
            if (Gdx.input.justTouched()) {
                startOrBack(Gdx.input.getY());

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
    private void startOrBack(int getY) {
        // Back
        if(getY < 340) {
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }
        // Start Game
        else if (getY > 350) {
            this.dispose();
            game.setScreen(new MainGameScreen(game));

        }
    }

    private void pickAMap(int getX) {

        int arrowMidPoint = X_PLACEMENT_ARROW + ARROW_WIDTH + (DISTANCE_BETWEEN_ARROW - ARROW_WIDTH) / 2 + DISTANCE_BETWEEN_MENU_LINES;
        if(getX > arrowMidPoint) {
            handleMapNumber(true);
        } else {
            handleMapNumber(false);

        }
    }

    private void numberOfEnemies(int getX) {
        int arrowMidPoint = X_PLACEMENT_ARROW + ARROW_WIDTH + (DISTANCE_BETWEEN_ARROW - ARROW_WIDTH) / 2;
        if(getX > arrowMidPoint) {

            handleNumberOfEnemies(true);
        } else {
            handleNumberOfEnemies(false);

        }

    }
    private void drawMap(int number) {
        game.batch.draw(maps.get(number), X_PLACEMENT_MENU_LINES + DISTANCE_BETWEEN_MENU_LINES + 20, Y_PLACEMENT_MENU_LINES + 170, 200, 25);
    }

    private void drawNumbersOfEnemies(int number) {
        game.batch.draw(numbers.get(number), X_PLACEMENT_MENU_LINES + ARROW_WIDTH, Y_PLACEMENT_MENU_LINES + 150, 50, 50);
    }

    private void handleMapNumber(Boolean newmap) {
        if(newmap) {
            if(mapNumber < maxMapNumber - 1) {

                mapNumber++;
            }
        }
        else {
            if(mapNumber > minMapNumber - 1) {
                mapNumber--;
            }
        }
    }


    // Makes sure that the max/min number of enemies is not breached
    private void handleNumberOfEnemies(Boolean moreEnemies) {
        if(moreEnemies) {
            if(numberOfEnemies < maxNumberOfEnemies - 1) {
                numberOfEnemies++;
            }
        }
        else {
            if(numberOfEnemies > minNumberOfEnemies - 1) {
                numberOfEnemies--;
            }
        }
    }



    private void fillTextTexture() {
        one = new Texture("assets/gameLobby/text/one.png");
        two = new Texture("assets/gameLobby/text/two.png");
        three = new Texture("assets/gameLobby/text/three.png");
        four = new Texture("assets/gameLobby/text/four.png");
        five = new Texture("assets/gameLobby/text/five.png");

        numbers.add(one);
        numbers.add(two);
        numbers.add(three);
        numbers.add(four);
        numbers.add(five);


        clusterCross = new Texture("assets/gameLobby/text/clusterCross.png");
        dizzyHighway = new Texture("assets/gameLobby/text/DizzyHighway.png");
        testingGround = new Texture("assets/gameLobby/text/TestingGround.png");
        pickMap = new Texture("assets/gameLobby/text/pickMap.png");
        pickEnemies = new Texture("assets/gameLobby/text/pickEnemies.png");
        gameLobby = new Texture("assets/gameLobby/text/GameLobby.png");

        maps.add(testingGround);
        maps.add(dizzyHighway);
        maps.add(clusterCross);

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

        // Draw the text
        game.batch.draw(pickMap, X_PLACEMENT_ARROW + DISTANCE_BETWEEN_MENU_LINES + 5, 328 , 180, ARROW_HEIGHT -10);
        game.batch.draw(pickEnemies, X_PLACEMENT_ARROW - 5, 328, 180, ARROW_HEIGHT);
        game.batch.draw(gameLobby, X_PLACEMENT_MENU_LINES,400, 400, 68);

        drawNumbersOfEnemies(numberOfEnemies);
        drawMap(mapNumber);
    }
}

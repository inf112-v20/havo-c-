package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MainGameScreen extends InputAdapter implements Screen {

    Robo game;
    GUI gui;
    private BitmapFont font;


    // Map related elements
    private OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();
    private Board gameBoard = new Board(mapLoader.load("assets/Testing Grounds.tmx"));

    // Variables for Player
    private IPlayer player;
    private MonkeyAI monkey;
    ArrayList<IPlayer> players;
    private CardDeck deck = new CardDeck();


    // Width and Height og the grid
    private final int BOARD_WIDTH = gameBoard.getBoard().getWidth();
    private final int BOARD_HEIGHT = gameBoard.getBoard().getHeight();



    public MainGameScreen(Robo robo) {

        this.game = robo;
        Vector2 startLoc = new Vector2(0,0);


        Vector2 tempStartLoc = new Vector2(9, 0);
        player = new Player(startLoc, Direction.NORTH, gameBoard);
        monkey = new MonkeyAI(tempStartLoc, Direction.NORTH, gameBoard);
        gui = new GUI(game, player);
        Gdx.input.setInputProcessor(this);

        players = new ArrayList<IPlayer>();
        players.add(player);
        players.add(monkey);
    }


    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
        // Input adapter shenanigans;

        // Code for setting up map
        camera.setToOrtho(false, BOARD_WIDTH, BOARD_HEIGHT);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(gameBoard.getMap(), 1/300f);
        mapRenderer.setView(camera);
        // Code for defining player and start location
    }


    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_WIDTH = 50;





    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // Code for drawing the GUI
        game.batch.begin();

        gui.drawButtons();
        gui.drawCards();
        gui.drawHealthbar(player.getHp());

        game.batch.end();


        // Renders map
        mapRenderer.render();

        // When user touches the cards
        if(Gdx.input.getX() > 50 * 10 && Gdx.input.getX() < 50 * 13 &&
                Gdx.input.getY() > 50 * 4 && Gdx.input.getY() < 50 *7) {
            if (Gdx.input.justTouched()) {
               gui.touchCards(Gdx.input.getX(), Gdx.input.getY());
            }
        }

        // When the user touches the buttons
        if(Gdx.input.getX() > 50 * 10 && Gdx.input.getX() < 50 * 13 &&
                Gdx.input.getY() > 50 * 9 && Gdx.input.getY() < 50 *10) {
            if (Gdx.input.justTouched()) {
                gui.touchedButtons(Gdx.input.getX());
            }
        }


        //Sets in player
        player.updatePlayerIcon();
        monkey.updatePlayerIcon();

    }

    public boolean keyUp(int keycode) {
        // Not done yet, will get possiblity of returning False when illegal moves are coded in
        // Logic gate for movement related input
        if (keycode == Input.Keys.W){
            player.Move(player.getPlayerDir());
        }
        else if (keycode == Input.Keys.Q || keycode == Input.Keys.E) {
            if (keycode == Input.Keys.Q) {
                player.Turn(TurnDirection.LEFT);
            } else {
                player.Turn(TurnDirection.RIGHT);
            }
        }
        // Checks if player is dead before allowing player to respawn
        else if (player.getPlayerState() == PlayerState.DEAD && keycode == Input.Keys.R){
            // Barebones respawn system that feeds in start coordinates and direction, a better one will be developed later
            player.respawn(0,0, Direction.NORTH);
        }
        // Checks if player is standing on special tiles
        gameBoard.checkForSpecialTiles(player);

        return true;
    }

    public void doTurn() {
        if (player.getPowerdown()) {
            // Insert code for powerdown here
        }
        // Insert delay here
        deck.dealCards(player);
        for (int j = 0; players.size() > j; j++) {
            if (player.getReady())
            // Insert delay here that allows players to choose their cards
            for (Integer i = 0; i < 7; i++) {
                // Must be improved to make card priority a thing
                player.playHand(i);
                // Must be improved so that the different parts act in the correct order
                gameBoard.checkForSpecialTiles(player);
            }
            // Insert cleanup phase here
            deck.collectCards(player);
        }
        if (player.getPowerdown()) {
            player.bootUp();
        }
    }


    @Override
    public void resize(int i, int i1) {

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


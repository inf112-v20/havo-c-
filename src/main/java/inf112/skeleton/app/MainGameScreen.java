package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class MainGameScreen implements Screen {

    Robo game;
    public SpriteBatch batch;
    private BitmapFont font;
    Texture move1;
    Texture moveRight;
    Texture moveLeft;

    // Map related elements
    private OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();
    private Board gameBoard = new Board(mapLoader.load("assets/Testing Grounds.tmx"));

    // Variables for Player
    private Player player;

    // Width and Height og the grid
    private final int BOARD_WIDTH = gameBoard.getPlayerLayer().getWidth();
    private final int BOARD_HEIGHT = gameBoard.getPlayerLayer().getHeight();


    public MainGameScreen(Robo robo) {

        this.game = robo;
        move1 = new Texture("assets/Move1.png");
        moveRight = new Texture("assets/moveright.png");
        moveLeft = new Texture("assets/moveleft.png");
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
        Vector2 startLoc = new Vector2(0,0);
        player = new Player(startLoc, Direction.NORTH, gameBoard.getPlayerLayer());




    }


    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_WIDTH = 50;





    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0, 22, 22, 29);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // Code for drawing the GUI
        game.batch.begin();
        game.batch.draw(move1, BUTTON_HEIGHT * 11 - 10, BUTTON_WIDTH * 5, 50, 50);
        game.batch.draw(moveRight, BUTTON_HEIGHT * 12 - 10, BUTTON_WIDTH * 5, 50, 50);
        game.batch.draw(moveLeft, BUTTON_HEIGHT * 13 - 10, BUTTON_WIDTH * 5, 50, 50);

        game.batch.end();


        // Renders map
        mapRenderer.render();



        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            keyUp(Input.Keys.W);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            keyUp(Input.Keys.Q);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            keyUp(Input.Keys.E);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            keyUp(Input.Keys.R);
        }

        if(Gdx.input.getX() > 50 * 11 && Gdx.input.getX() < 50 * 12 &&
                Gdx.input.getY() > 50 * 4 && Gdx.input.getY() < 50 *5) {
            if (Gdx.input.justTouched()) {
                keyUp(Input.Keys.W);
            }
        }
        if(Gdx.input.getX() > 50 * 12 && Gdx.input.getX() < 50 * 13 &&
                Gdx.input.getY() > 50 * 4 && Gdx.input.getY() < 50 *5) {
            if (Gdx.input.justTouched()) {
                keyUp(Input.Keys.E);
            }
        }
        if(Gdx.input.getX() > 50 * 13 && Gdx.input.getX() < 50 * 14 &&
                Gdx.input.getY() > 50 * 4 && Gdx.input.getY() < 50 *5) {
            if (Gdx.input.justTouched()) {
                keyUp(Input.Keys.Q);
            }
        }


        //Sets in player
        player.updatePlayerIcon();



    }

    public void  keyUp(int keycode) {
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
        gameBoard.checkForSpecialTiles(player, Boolean.FALSE);



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

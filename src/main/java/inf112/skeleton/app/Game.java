package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.sun.prism.impl.ps.CachingEllipseRep;

import javax.print.attribute.IntegerSyntax;

public class Game extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    // Variables for Gameboard
    private TiledMap tiledMap;
    // Board is not yet in use but are going to be relevant later in development
    private Board gameBoard;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();
    // Variables for Player
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
        // Input adapter shenanigans
        Gdx.input.setInputProcessor(this);
        // Code for setting up map
        gameBoard = new Board(mapLoader.load("assets/map.tmx"));
        camera.setToOrtho(false, 5, 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(gameBoard.getMap(), 1/300f);
        mapRenderer.setView(camera);
        // Code for defining player and start location
        Vector2 startLoc = new Vector2(0,0);
        player = new Player(startLoc, Direction.NORTH, gameBoard.getPlayerLayer());
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Renders map
        mapRenderer.render();
        // Checks if player is standing on special tiles
        gameBoard.checkForSpecialTiles(player);
        //Sets in player
        player.updatePlayerIcon();
    }

    @Override
    public boolean keyUp(int keycode) {
        // Not done yet, will get possiblity of returning False when illegal moves are coded in
        // Logic gate for movement related input
        if (keycode == Input.Keys.W){
            player.Move();
        }
        else if (keycode == Input.Keys.Q || keycode == Input.Keys.E){
            player.Turn(keycode);
        }
        else if (player.getPlayerState() == PlayerState.DEAD && keycode == Input.Keys.R){
            player.respawn(0,0, Direction.NORTH);
        }
        return true;
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
}

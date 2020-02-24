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
    private TiledMapTileLayer Board;
    private TiledMapTileLayer Holes;
    private TiledMapTileLayer Flags;
    private TiledMapTileLayer PlayerLayer;
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
        tiledMap = mapLoader.load("assets/map.tmx");
        Holes = (TiledMapTileLayer) tiledMap.getLayers().get("Holes");
        Flags = (TiledMapTileLayer) tiledMap.getLayers().get("Flags");
        PlayerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("PlayerLayer");
        camera.setToOrtho(false, 5, 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/300f);
        mapRenderer.setView(camera);
        // Code for defining player and start location
        Vector2 startLoc = new Vector2(0,0);
        player = new Player(startLoc, PlayerLayer);
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
        //Sets in player
        player.setPlayerState(PlayerState.ALIVE);
        //Checks if the player is on any Special Tiles
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();

        if (Flags.getCell(xLoc,yLoc) != null){
            player.setPlayerState(PlayerState.WINNER);
        }
        else if (Holes.getCell(xLoc,yLoc) != null){
            player.setPlayerState(PlayerState.DEAD);
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        // Not done yet, will get possiblity of returning False when illegal moves are coded in
        // Logic gate for movement related input
        if (keycode == Input.Keys.W || keycode == Input.Keys.A || keycode == Input.Keys.S || keycode == Input.Keys.D){
            player.Move(keycode);
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

package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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

import javax.print.attribute.IntegerSyntax;

public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    // Variables for Gameboard
    private TiledMap tiledMap;
    // Board1 and 2 are not yet in use but are going to be relevant later in development
    private TiledMapTileLayer Board1;
    private TiledMapTileLayer Board2;
    private TiledMapTileLayer PlayerLayer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();
    // Variables for Player
    private TiledMapTileLayer.Cell playerWonCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerDiedCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
    private Vector2 playerLoc;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
        // Code for setting up map
        tiledMap = mapLoader.load("assets/map.tmx");
        PlayerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("PlayerLayer");
        camera.setToOrtho(false, 5, 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/300f);
        mapRenderer.setView(camera);
        // Code for defining player graphics and start location
        TextureRegion[][] spiller = new TextureRegion(new Texture("assets/player.png")).split(300,300);
        playerCell.setTile(new StaticTiledMapTile(spiller[0][0]));
        playerDiedCell.setTile(new StaticTiledMapTile(spiller[0][1]));
        playerWonCell.setTile(new StaticTiledMapTile(spiller[0][2]));
        playerLoc = new Vector2(0,0);
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
        Integer xLoc = Math.round(playerLoc.x);
        Integer yLoc = Math.round(playerLoc.y);
        PlayerLayer.setCell(xLoc,yLoc, playerCell);
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

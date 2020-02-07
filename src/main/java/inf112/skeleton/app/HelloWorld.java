package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap tiledMap;
    private TiledMapTileLayer boardLayer;
    private  TiledMapTileLayer playerLayer;
    private  TiledMapTileLayer holeLayer;
    private  TiledMapTileLayer flagLayer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
        tiledMap = new TmxMapLoader().load("map.tmx");
       // mapLoader.load("assets/map.tmx");
        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        camera.setToOrtho(false, 5, 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/300f);
        mapRenderer.setView(camera);
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

        mapRenderer.render();
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

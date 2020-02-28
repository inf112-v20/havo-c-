package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    /*
        This class test the Board class
     */
    Board board;

    TiledMap Map;
    TiledMapTileLayer Holes;
    TiledMapTileLayer Flags;
    TiledMapTileLayer Players;

    @Before
    public void setUp() throws Exception {
        TmxMapLoader mapLoader = new TmxMapLoader();
        Board gameBoard = new Board(mapLoader.load("assets/map.tmx"));

        board = new Board(Map);

        this.Map = Map;

        this.Holes = (TiledMapTileLayer) Map.getLayers().get("Holes");
        this.Flags = (TiledMapTileLayer) Map.getLayers().get("Flags");
        this.Players = (TiledMapTileLayer) Map.getLayers().get("PlayerLayer");
    }

    @Test
    public void TestIfTheBoardGetsImportedMap() {
        assertEquals(Map, board.getMap());
    }
}

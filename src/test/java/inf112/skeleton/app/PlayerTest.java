package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    TmxMapLoader mapLoader = new TmxMapLoader();
    Board board;
    Player player;

    @Before
    public void SetUp(){
        board = new Board(mapLoader.load("assets/map.tmx"));
        Vector2 startLoc = new Vector2(0,0);
        player = new Player(startLoc, Direction.NORTH, board.getPlayerLayer());
    }

    @Test
    public void playerIsCreatedSucessfully() {
        // Checks startLocation
        assertEquals(new Vector2(0,0), player.getPlayerLoc());
        // Checks start direction
        assertEquals(Direction.NORTH, player.getPlayerDir());
        // Checks that it is connected to playerLayer
    }
}

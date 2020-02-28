package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import static org.junit.Assert.assertEquals;

public class dirCtrlTest {
    dirCtrl dirCtrl = new dirCtrl();

    @Test
    public void turnLeftTest() {
        assertEquals(Direction.WEST, dirCtrl.turnLeft(Direction.NORTH));
        assertEquals(Direction.SOUTH, dirCtrl.turnLeft(Direction.WEST));
        assertEquals(Direction.EAST, dirCtrl.turnLeft(Direction.SOUTH));
        assertEquals(Direction.NORTH, dirCtrl.turnLeft(Direction.EAST));
    }
}

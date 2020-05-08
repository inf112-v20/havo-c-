package inf112.skeleton.app;

import inf112.skeleton.app.Enums.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class dirCtrlTest {
    DirCtrl dirCtrl = new DirCtrl();

    @Test
    public void turnLeftTest() {
        assertEquals(Direction.WEST, dirCtrl.turnLeft(Direction.NORTH));
        assertEquals(Direction.SOUTH, dirCtrl.turnLeft(Direction.WEST));
        assertEquals(Direction.EAST, dirCtrl.turnLeft(Direction.SOUTH));
        assertEquals(Direction.NORTH, dirCtrl.turnLeft(Direction.EAST));
    }

    @Test
    public void turnRightTest() {
        assertEquals(Direction.WEST, dirCtrl.turnRight(Direction.SOUTH));
        assertEquals(Direction.SOUTH, dirCtrl.turnRight(Direction.EAST));
        assertEquals(Direction.EAST, dirCtrl.turnRight(Direction.NORTH));
        assertEquals(Direction.NORTH, dirCtrl.turnRight(Direction.WEST));
    }

    @Test
    public void invertDirectionTest() {
        assertEquals(Direction.WEST, dirCtrl.invertDirection(Direction.EAST));
        assertEquals(Direction.SOUTH, dirCtrl.invertDirection(Direction.NORTH));
        assertEquals(Direction.EAST, dirCtrl.invertDirection(Direction.WEST));
        assertEquals(Direction.NORTH, dirCtrl.invertDirection(Direction.SOUTH));
    }
}

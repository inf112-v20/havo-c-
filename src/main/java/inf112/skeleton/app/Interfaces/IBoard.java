package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Enums.Direction;

public interface IBoard {
    void checkForSpecialTiles(IPlayer player);
    void checkHoles(IPlayer player);
    boolean wallCheck(Integer xLoc, Integer yLoc, Direction dir, Boolean second);
}

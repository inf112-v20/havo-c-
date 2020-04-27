package inf112.skeleton.app;

public interface IBoard {
    void checkForSpecialTiles(IPlayer player);
    void checkHoles(IPlayer player);
    boolean wallCheck(IPlayer player, Direction dir, Boolean second);
}

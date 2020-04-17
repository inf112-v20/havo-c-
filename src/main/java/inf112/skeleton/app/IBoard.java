package inf112.skeleton.app;

public interface IBoard {
    void checkForSpecialTiles(Player player);
    void checkHoles(Player player);
    boolean wallCheck(Player player, Direction dir, Boolean second);
}

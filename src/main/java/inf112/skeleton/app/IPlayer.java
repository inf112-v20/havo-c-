package inf112.skeleton.app;

public interface IPlayer {
    void Move(Direction dir);

    void Turn(TurnDirection dir);

    void setPlayerState(PlayerState newState);

    void respawn(Integer xCoord, Integer yCoord, Direction dir);

    void updatePlayerIcon();


}

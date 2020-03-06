package inf112.skeleton.app;

public interface IPlayer {
    void Move();

    void Turn(int keycode);

    void setPlayerState(PlayerState newState);

    void respawn(Integer xCoord, Integer yCoord, Direction dir);

    void updatePlayerIcon();


}

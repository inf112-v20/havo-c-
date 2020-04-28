package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public interface IPlayer {



    void Move(Direction dir);

    void Turn(TurnDirection dir);

    void setPlayerState(PlayerState newState);

    void respawn(Integer xCoord, Integer yCoord, Direction dir);

    void updatePlayerIcon();

    void takeDamage(Integer amount);

    void emptyHand();

    void playHand(Integer i);

    void addToHand(Card card);

    Integer getX();
    Integer getY();
    Direction getPlayerDir();
    Integer getLives();
    Integer getHp();
    PlayerState getPlayerState();
    Integer getFlags();

    void visitFlag();


    ArrayList<Card> getHand();

    Boolean getPowerdown();

    void setPowerdown(Boolean value);

    Boolean getReady();

    void setReady(Boolean value);

    void bootUp();

    void powerdown();

    public Vector2 getPlayerloc();

}




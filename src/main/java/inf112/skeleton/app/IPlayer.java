package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public interface IPlayer {



    void Move(Direction dir);

    void Turn(TurnDirection dir);

    void fireLaser();

    void setPlayerState(PlayerState newState);

    void respawn(Direction dir);

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

    void setPlayerDir(Direction dir);

    void setReady(Boolean value);

    void bootUp();

    void powerdown();

    Vector2 getPlayerloc();

    void playFullHand();

    void addCardvalues(Integer value);

    void clearCardValues();

    Integer getOneCardValue(Integer index);

    ArrayList<Integer> getCardValues();

    Boolean isPlayer();

    void setSpawnPoint(Vector2 spawnVector);

    Boolean isYou();
}




package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;

import java.util.InputMismatchException;

public class Card implements ICard {
    String command;
    private int value;
    DirCtrl dirCtrl = new DirCtrl();
    IPlayer owner;

    public Card(String command){
        this.command = command;
    }
    // Playing the card itself
    public void playCard(IPlayer owner){
        // Wont play card unless a player has it
        if(command == "Move1"){
            owner.Move(owner.getPlayerDir());
        }
        else if(command == "Move2"){
            for (Integer i=0; i < 2; i++) {
                owner.Move(owner.getPlayerDir());
            }
        }
        else if(command == "Move3"){
            for (Integer i=0; i < 3; i++) {
                owner.Move(owner.getPlayerDir());
            }
        }
        else if (command == "MoveBack"){
            owner.Move(dirCtrl.invertDirection(owner.getPlayerDir()));
        }
        else if(command == "TurnRight"){
            owner.Turn(TurnDirection.RIGHT);
        }
        else if(command == "TurnLeft"){
            owner.Turn(TurnDirection.LEFT);
        }
        else if(command == "Turn180"){
            owner.Turn(TurnDirection.BACKWARDS);
        }
    }

    // Setup for card value system
    public String getCommand(){
        return command;
    }

    public Integer getValue() { return value;}

    public  void setValue(Integer newValue) {value = newValue;}

    public void setOwner(IPlayer player) {owner = player;}

    public  IPlayer getOwner() {return owner;}

    public  void clearOwner() {owner = null;}


}

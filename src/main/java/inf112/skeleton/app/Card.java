package inf112.skeleton.app;

import com.badlogic.gdx.Input;

import java.util.InputMismatchException;

public class Card implements ICard {
    String command;
    DirCtrl dirCtrl = new DirCtrl();

    public Card(String command){
        this.command = command;
    }
    // Playing the card itself
    public void playCard(Player owner){
        // Wont play card unless a player has it
        if(command == "Move1"){
            owner.Move(owner.getPlayerDir());
        }
        else if(command == "Move2"){
            owner.Move(owner.getPlayerDir());
            owner.Move(owner.getPlayerDir());
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
    public String getCommand(){
        return command;
    }
}

package inf112.skeleton.app;

import com.badlogic.gdx.Input;

import java.util.InputMismatchException;

public class Card {
    String command;
    Player owner;

    public Card(String command){
        this.command = command;
        this.owner = null;
    }
    // Dealing cards and putting them back into deck
    public void getDealt(Player newOwner){
        owner = newOwner;
    }
    public void getDecked(){
        owner = null;
    }
    // Playing the card itself
    public void playCard(){
        // Wont play card unless a player has it
        if(owner != null){
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
            else if(command == "TurnRight"){
                owner.Turn(TurnDirection.RIGHT);
            }
            else if(command == "TurnLeft"){
                owner.Turn(TurnDirection.LEFT);
            }
        }
    }
    // Get class elements
    public Player getPlayer(){
        return owner;
    }
    public String getCommand(){
        return command;
    }
}

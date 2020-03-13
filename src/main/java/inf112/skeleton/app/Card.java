package inf112.skeleton.app;

import com.badlogic.gdx.Input;

import java.util.InputMismatchException;

public class Card {
    String command;
    Player player;

    public Card(String command){
        this.command = command;
        this.player = null;
    }

    public void getDealt(Player newOwner){
        player = newOwner;
    }

    public void getDecked(){
        player = null;
    }

    public void playCard(){
        if(player != null){
            if(command == "Move1"){
                player.Move(player.getPlayerDir());
            }
            else if(command == "Move2"){
                player.Move(player.getPlayerDir());
                player.Move(player.getPlayerDir());
            }
            else if(command == "Move3"){
                for (Integer i=0; i < 3; i++) {
                    player.Move(player.getPlayerDir());
                }
            }
            else if(command == "TurnRight"){
                player.Turn(Input.Keys.E);
            }
            else if(command == "TurnLeft"){
                player.Turn(Input.Keys.Q);
            }
        }
    }
}

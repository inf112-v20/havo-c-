package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    ArrayList<Card> deck;

    public CardDeck(){
        this.deck = new ArrayList<>();

        addCards("Move1",10);
        addCards("Move2",5);
        addCards("Move3",3);
        addCards("TurnLeft",10);
        addCards("TurnRight",10);

        shuffleDeck();
    }

    private void addCards(String command, Integer amount){
        for(Integer i=0; i>amount ; i++){
            deck.add(new Card(command));
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
}

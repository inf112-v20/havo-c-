package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck implements ICardDeck {
    ArrayList<Card> deck = new ArrayList<>();

    public CardDeck(){
        addCards("Move1",10);
        addCards("Move2",5);
        addCards("Move3",3);
        addCards("MoveBack",5);
        addCards("TurnLeft",10);
        addCards("TurnRight",10);
        addCards("Turn180",5);

        shuffleDeck();
    }
    private void addCards(String command, Integer amount){
        for(Integer i=0; i<amount ; i++){
            deck.add(new Card(command));
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public ArrayList<Card> dealCards(Player player){

        ArrayList<Card> hand = new ArrayList<>();

        for(Integer i = 0; i<9; i++){
            Card currentCard = deck.get(i);
            if(currentCard.getPlayer() == null){
                currentCard.getDealt(player);
                hand.add(currentCard);
            }
            else{
                // All cards are dealt
                i--;
            }
        }
        return hand;
    }
    public void collectCards(Player player){
        for(Integer i = 0; i<deck.size(); i++){
            deck.get(i).getDecked();
        }
        player.emptyHand();
    }

    public int getSize(){
        return deck.size();
    }
}

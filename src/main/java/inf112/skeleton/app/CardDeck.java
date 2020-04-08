package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck implements ICardDeck {
    ArrayList<Card> deck;

    public CardDeck(){
        this.deck = new ArrayList<>();

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
        for(Integer i=0; i>amount ; i++){
            deck.add(new Card(command));
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void dealCards(Player player){
        for(Integer i = 0; i<5; i++){
            Card currentCard = deck.get(0);
            if(currentCard.getPlayer() == null){
                currentCard.getDealt(player);
                player.addToHand(currentCard);
            }
            else{
                // All cards are dealt
                break;
            }
            deck.remove(currentCard);
            deck.add(currentCard);
        }
    }
    public void collectCards(Player player){
        for(Integer i = 0; i<deck.size(); i++){
            deck.get(i).getDecked();
        }
        player.emptyHand();
    }
}

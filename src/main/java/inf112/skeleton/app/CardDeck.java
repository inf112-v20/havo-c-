package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck implements ICardDeck {
    // The big card deck
    ArrayList<Card> deck = new ArrayList<>();

    // Constructor that calls the addCards function, the amount section makes it easier to nerf/buff card-rng
    // definition: "card-rng" the options the player/AI will have in card, will be random so therefore card-rng
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
    // Adds card to the big card deck containing all the cards
    private void addCards(String command, Integer amount){
        for(Integer i=0; i<amount ; i++){
            deck.add(new Card(command));
        }
    }

    // Shuffles all the cards in the big card deck
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    // Adds the right amount to the hand
    // hand is the cards the player/AI can pick amongst
    // ATM both the AI and player get each a big card deck so card-rng does not affect each other
    public ArrayList<Card> dealCards(){

        ArrayList<Card> hand = new ArrayList<>();

        for(Integer i = 0; i < 9; i++) {
            Card currentCard = deck.get(i);
            hand.add(currentCard);
        }
        return hand;
    }
    public void collectCards(IPlayer player){
        player.emptyHand();
    }

    public int getSize(){
        return deck.size();
    }
}

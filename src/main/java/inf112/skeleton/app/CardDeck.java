package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck implements ICardDeck {
    // The big card deck
    ArrayList<Card> deck = new ArrayList<>();

    private int turn180 = 10;
    private int turnLeft = 70;
    private int turnRight = 80;
    private int backup = 430;
    private int move1 = 490;
    private int move2 = 670;
    private int move3 = 790;

    /*
backup: 6 kort (430 - 480)
u-turn: 6 kort (10 - 60)
rotate right: 18 kort (80-420, intervall 20)
rotate left: 18 kort (70-410, intervall 20)
move 1: 18 kort (490 - 650, intervall 10)
move 2: 12 kort (670 - 780, intervall 10)
move 3: 6 kort (790 - 840, intervall 10)
 */
    // Constructor that calls the addCards function, the amount section makes it easier to nerf/buff card-rng
    // definition: "card-rng" the options the player/AI will have in card, will be random so therefore card-rng
    public CardDeck(){
        addCards("Move1",18);
        addCards("Move2",12);
        addCards("Move3",6);
        addCards("MoveBack",6);
        addCards("TurnLeft",18);
        addCards("TurnRight",18);
        addCards("Turn180",6);

        shuffleDeck();
    }
    // Adds card to the big card deck containing all the cards
    private void addCards(String command, Integer amount){
        for(Integer i=0; i<amount ; i++){
            Card newCard = new Card(command);
            newCard.setValue(handleCardValue(command));
            deck.add(newCard);
        }
    }

    // Shuffles all the cards in the big card deck
    public void shuffleDeck(){

        Collections.shuffle(deck);
    }

    // Adds the right amount to the hand
    // hand is the cards the player/AI can pick amongst
    // ATM both the AI and player get each a big card deck so card-rng does not affect each other
    public ArrayList<Card> dealCards(IPlayer owner){

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

    public ArrayList<Card> getDeck() {
        return deck;
    }

    private Integer handleCardValue(String command) {
        int value = 0;
        if(command.equals("Move1")) {
            value = move1;
            move1 = move1 + 10;
        }
        else if(command.equals("Move2")) {
            value = move2;
            move2 = move2 + 10;
        }
        else if(command.equals("Move2")) {
            value = move2;
            move2 = move2 + 10;
        }
        else if(command.equals("Move3")) {
            value = move3;
            move3 = move3 + 10;
        }
        else if(command.equals("MoveBack")) {
            value = backup;
            backup = backup + 10;
        }
        else if(command.equals("TurnLeft")) {
            value = turnLeft;
            turnLeft = turnLeft + 20;
        }
        else if(command.equals("TurnRight")) {
            value = turnRight;
            turnRight = turnRight + 20;
        }
        else if(command.equals("Turn180")) {
            value = turn180;
            turn180 = turn180 + 10;
        }


        return value;
    }
}

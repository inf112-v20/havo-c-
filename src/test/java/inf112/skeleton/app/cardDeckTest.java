package inf112.skeleton.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class cardDeckTest {
    CardDeck deck = new CardDeck();

    @Test
    public void deckSizeTest() {
        assertEquals(84, deck.getSize());
    }

    @Test
    public void checkThatTheDeckHaveRightAmountOfTheRightCards() {
        String command;
        int move1 = 0;
        int move2 = 0;
        int move3 = 0;
        int moveBack = 0;
        int turnRight = 0;
        int turnLeft = 0;
        int turn180 = 0;
        for (int i = 0; i < deck.getSize(); i++) {
            command = deck.getDeck().get(i).getCommand();
            if(command == "Move1"){
                move1++;
            }
            else if(command == "Move2"){
                move2++;
            }
            else if(command == "Move3"){
                move3++;
            }
            else if (command == "MoveBack"){
                moveBack++;
            }
            else if(command == "TurnRight"){
              turnRight++;
            }
            else if(command == "TurnLeft"){
               turnLeft++;
            }
            else if(command == "Turn180"){
               turn180++;
            }
        }
        assertEquals(18, move1);
        assertEquals(12, move2);
        assertEquals(6, move3);
        assertEquals(6, moveBack);
        assertEquals(18, turnRight);
        assertEquals(18, turnLeft);
        assertEquals(6, turn180);
    }
}

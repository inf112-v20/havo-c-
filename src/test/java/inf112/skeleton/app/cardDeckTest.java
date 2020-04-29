package inf112.skeleton.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class cardDeckTest {
    CardDeck deck = new CardDeck();

    @Test
    public void deckSizeTest() {
        assertEquals(48, deck.getSize());
    }
}

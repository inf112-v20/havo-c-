package inf112.skeleton.app;

import java.util.ArrayList;

public interface ICardDeck {

    void shuffleDeck();

    ArrayList<Card> dealCards();

    void collectCards(IPlayer player);
}

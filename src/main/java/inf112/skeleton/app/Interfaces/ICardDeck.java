package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Card;

import java.util.ArrayList;

public interface ICardDeck {

    void shuffleDeck();

    ArrayList<Card> dealCards(IPlayer owner);

    void collectCards(IPlayer player);
}

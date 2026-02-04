package card;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    private List<Card> deckOfCards;
    private String name;

    public CardGame(String name) {
        this.name = name;
        this.deckOfCards = new ArrayList<>();
        populateDeckOfCards(); // Populate deck when CardGame instance is created
    }

    public List<Card> getDeck() {
        // Should display what is returned from toString method on Card class
        return deckOfCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeckOfCards(List<Card> deckOfCards) {
        this.deckOfCards = deckOfCards;
    }

    public void populateDeckOfCards() {
        String[] suits = { "♥", "♦", "♣", "♠" };
        String[] symbols = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

        for (String suit : suits) {
            for (int i = 0; i < symbols.length; i++) {
                deckOfCards.add(new Card(suit, symbols[i], values[i]));
            }
        }
    }
}

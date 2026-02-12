package card;

import java.util.*;

public class CardGame {
    protected List<Card> deckOfCards;
    protected final String name;

    public CardGame(String name) {
        this.name = name;
        this.deckOfCards = new ArrayList<>();
        populateDeckOfCards(); // Populate deck when CardGame instance is created
    }

    public String getName() {
        return name;
    }

    public void setDeckOfCards(List<Card> deckOfCards) {
        this.deckOfCards = deckOfCards;
    }

    public List<Card> getDeck() {
        // Should display what is returned from toString method on Card class
        return deckOfCards;
    }

//    Takes a card from the top of the deck and returns it
    public Card dealCard() {
        return deckOfCards.removeLast();
    }

    public void shuffleDeck() {
        Collections.shuffle(deckOfCards);
    }

    public void sortDeckInNumberOrder() {
        deckOfCards.sort(Comparator.comparing(card -> card.VALUE));
    }

    public void sortDeckIntoSuits() {
        deckOfCards.sort(Comparator.comparing(card -> card.SUIT));
    }

    public void populateDeckOfCards() {
        String[] suits = { "♥", "♦", "♣", "♠" };
        Map<String, Integer> symbolsAndValues = new LinkedHashMap<>();
        symbolsAndValues.put("2", 2);
//        symbolsAndValues.put("3", 3);
//        symbolsAndValues.put("4", 4);
//        symbolsAndValues.put("5", 5);
//        symbolsAndValues.put("6", 6);
//        symbolsAndValues.put("7", 7);
//        symbolsAndValues.put("8", 8);
//        symbolsAndValues.put("9", 9);
//        symbolsAndValues.put("10", 10);
//        symbolsAndValues.put("J", 11);
//        symbolsAndValues.put("Q", 12);
//        symbolsAndValues.put("K", 13);
//        symbolsAndValues.put("A", 14);

        for (String suit : suits) {
            for (String symbol: symbolsAndValues.keySet()) {
                deckOfCards.add(new Card(suit, symbol, symbolsAndValues.get(symbol)));
            }
        }
    }
}

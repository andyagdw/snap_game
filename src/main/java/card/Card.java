package card;

public class Card {
    protected final String SUIT; // Unicode characters of heart, club, diamond and spade
    protected final String SYMBOL; // 2 - A (Ace)
    protected final Integer VALUE; // 2 - 14


    public Card(String suit, String symbol, Integer value) {
        this.SUIT = suit;
        this.SYMBOL = symbol;
        this.VALUE = value;
    }

    protected String getSUIT() {
        return SUIT;
    }

    protected String getSYMBOL() {
        return SYMBOL;
    }

    protected int getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "Card--------{" +
                "SUIT='" + SUIT + '\'' +
                ", SYMBOL='" + SYMBOL + '\'' +
                ", value=" + VALUE +
                "}\n";
    }
}

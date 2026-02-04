package card;

public class Card {
    private final String SUIT; // Unicode characters of heart, club, diamond and spade
    private final String SYMBOL; // 2 - A (Ace)
    private final int VALUE; // 2 - 14


    public Card(String suit, String symbol, int value) {
        this.SUIT = suit;
        this.SYMBOL = symbol;
        this.VALUE = value;
    }

    public String getSUIT() {
        return SUIT;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    public int getValue() {
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

package card;

import java.util.Scanner;

public class Snap extends CardGame {
    protected Card previousCard;
    protected Card currentCard;
    protected boolean gameIsRunning;

    public Snap(String name) {
        super(name);
    }

    public Card getPreviousCard() {
        return previousCard;
    }

    public void setPreviousCard(Card previousCard) {
        this.previousCard = previousCard;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public boolean isGameIsRunning() {
        return gameIsRunning;
    }

    public void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }

    public boolean checkIsSnap(Card currentCard, Card previousCard) {
        return previousCard.getSYMBOL().equals(currentCard.getSYMBOL());
    }

    public void displayGameInstructions(String name) {
        System.out.printf("\nHi %s, welcome to Snap!\n", name);
        System.out.print("The rules of the game are as follows:\n");
        System.out.print("1. By pressing `enter` in the command line, you take your turn.\n");
        System.out.print("2. Each turn, a new card is dealt from the deck.\n");
        System.out.print("3. The game continues until two cards in a row have the same symbol, at which point you" +
                " win and the game ends.\n");
        System.out.print("4. By the time all cards have been dealt from the deck and there has not been a match, you lose.\n\n");
    }

    public int getNumOfTimesToShuffleDeck(Scanner scanner) {
        System.out.print("Before starting the game, how many times would you like to shuffle the deck: ");
        return scanner.nextInt();
    }

    public void endGame() {
        setGameIsRunning(false);
    }

    public void playGame(Scanner scanner) {
        setGameIsRunning(true);
        while (isGameIsRunning()) {
            if (getDeck().isEmpty()) { // If deck is empty, user has failed to match previous card to current
                System.out.println("Game over! You have failed to match two cards.");
                endGame();
                break;
            } else {
                setCurrentCard(dealCard());
                System.out.println("Current Card: " + getCurrentCard());
                System.out.println("Previous Card: " + getPreviousCard());
                if (getPreviousCard() != null) {
                    if (checkIsSnap(getCurrentCard(), getPreviousCard())) {
                        System.out.println("You win!");
                        endGame();
                        break;
                    }
                }
                setPreviousCard(getCurrentCard());
                System.out.print("Press `enter` to deal next card. Enter any other key to end game:");
                String answer = scanner.nextLine().trim();
                if (!answer.isEmpty()) {
                    endGame();
                    break;
                }
            }
        }
    }

}

package card;

import player.Player;

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

    public void displayGameInstructions(String player1Name, String player2Name) {
        System.out.printf("\nHi %s and %s, welcome to Snap!\n", player1Name, player2Name);
        System.out.print("The rules of the game are as follows:\n");
        System.out.print("1. By pressing `enter` in the command line, you take your turn.\n");
        System.out.print("2. Each turn, a new card is dealt from the deck.\n");
        System.out.print("3. The game continues until two cards in a row have the same symbol, at which point the person" +
                " who drew the card that matches the symbol of the previous card wins and the game ends.\n");
        System.out.print("4. By the time all cards have been dealt from the deck and there has not been a match, the game ends in a tie.\n\n");
    }

    public int getNumOfTimesToShuffleDeck(Scanner scanner) {
        System.out.print("Before starting the game, how many times would you like to shuffle the deck: ");
        return scanner.nextInt();
    }

    public void endGame() {
        setGameIsRunning(false);
    }

    public void playGame(Scanner scanner, int mode) {
        setGameIsRunning(true);
        String gameOverSinglePlayerText = "Game over! You have failed to match two cards.";
        String gameOverMultiplayerText = "Game over! It was a tie.";

        while (isGameIsRunning()) {
            if (getDeck().isEmpty()) { // If deck is empty, user has failed to match previous card to current OR both players have failed to match
                if (mode == 1) {
                    System.out.println(gameOverSinglePlayerText);
                } else if (mode == 2) {
                    System.out.println(gameOverMultiplayerText);
                }
                endGame();
                break;
            } else {
                Player currentPlayer = Player.getNextPlayer();
                if (mode == 2) {
                    System.out.printf("%s's turn\n", currentPlayer);
                }
                System.out.print("Press `enter` to deal next card. Enter any other key to end game: ");
                String answer = scanner.nextLine().trim();
                if (!answer.isEmpty()) {
                    endGame();
                    break;
                }
                setCurrentCard(dealCard());
                System.out.println("\nCurrent Card: " + getCurrentCard());
                System.out.println("Previous Card: " + getPreviousCard() + "\n");
                if (getPreviousCard() != null) {
                    if (checkIsSnap(getCurrentCard(), getPreviousCard())) {
                        if (mode == 1) {
                            System.out.println("You win!");
                        } else if (mode == 2) {
                            System.out.printf("%s wins!!\n", currentPlayer);
                        }
                        endGame();
                        break;
                    }
                }
                setPreviousCard(getCurrentCard());
            }
        }
    }
}

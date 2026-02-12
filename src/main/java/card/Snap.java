package card;

import player.Player;

import java.util.Scanner;
import java.util.concurrent.*;

public class Snap extends CardGame {
    protected Card previousCard;
    protected Card currentCard;
    protected boolean gameIsRunning;

    public Snap(String name) {
        super(name);
    }

    protected Card getPreviousCard() {
        return previousCard;
    }

    protected void setPreviousCard(Card previousCard) {
        this.previousCard = previousCard;
    }

    protected Card getCurrentCard() {
        return currentCard;
    }

    protected void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    protected boolean isGameIsRunning() {
        return gameIsRunning;
    }

    protected void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }

    protected boolean checkIsSnap(Card currentCard, Card previousCard) {
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
        System.out.print("3. When a player draws a card with the same symbol as the previous card, AND types in `snap` " +
                "within two seconds, they win and the game ends.\n");
        System.out.println("4. If the player fails to type in `snap` within the timeframe, they lose the game.");
        System.out.print("5. By the time all cards have been dealt from the deck and there has not been a match, the game ends in a tie.\n\n");
    }

    public boolean checkUserWantsToPlayAgain(Scanner scanner) {
        System.out.print("Play again? y or n: ");
        String userWantsToPlayAgain = scanner.nextLine();
        return userWantsToPlayAgain.equalsIgnoreCase("y");
    }

    public void displayMainMenu() {
        System.out.println("Please enter a mode you would like to play:\n1. Single Player\n2. Multi-Player");
    }

    public int getNumOfTimesToShuffleDeck(Scanner scanner) {
        System.out.print("Before starting the game, how many times would you like to shuffle the deck: ");
        return scanner.nextInt();
    }

    public void endGame() {
        setGameIsRunning(false);
    }

    public void handleSinglePlayerCheckIsSnap() {
        System.out.println("You win!");
        endGame();
    }

//    public void handleMultiplayerCheckIsSnap(Player currentPLayer) {
//        System.out.printf("%s wins\n!", currentPLayer);
//    }

//    public void handleMultiplayerCheckIsSnap1(Scanner scanner) {
//        ExecutorService ex = Executors.newSingleThreadExecutor();
//
//        Callable<String> readTask = () -> {
//            System.out.print("SNAP TIME! Type 'SNAP' within two seconds to win....");
//            return scanner.nextLine();
//        };
//
//        Future<String> future = ex.submit(readTask);
//        try {
//            String input = future.get(2, TimeUnit.SECONDS);
//            if (!input.equalsIgnoreCase("snap")) {
//                System.out.println("\nFailed to type in 'SNAP'. " + Player.getNextPlayer() + " wins.");
//            }
//        } catch (TimeoutException te) {
//            System.out.println("\nFailed to type in 'SNAP'. " + Player.getNextPlayer() + " wins.");
//            future.cancel(true);
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            endGame();
//            ex.shutdownNow();
//        }
//    }

    public void handleMultiplayerCheckIsSnap(Scanner scanner, Player currentPlayer) {
        System.out.print("SNAP TIME! Type 'SNAP' within two seconds to win....");

        long startTime = System.currentTimeMillis(); // Since 1970
        String input = "";

        // Wait for user input for 2 seconds (check input in a loop)
        while (System.currentTimeMillis() - startTime < 2000) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
                break;  // Exit if input is provided
            }
        }

        if (input.equalsIgnoreCase("snap")) {
            System.out.println(currentPlayer + " wins." );
        } else {
            System.out.println("\nFailed to type in 'SNAP'. " + Player.getNextPlayer() + " wins.");
        }
        endGame();
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
            } else { // Deck is not empty
                Player currentPlayer = Player.getNextPlayer();
                if (mode == 2) { // Only display current player name in multiplayer mode
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
                if (getPreviousCard() != null) { // Runs after first card is dealt (previous card will always be null on first deal card)
                    if (checkIsSnap(getCurrentCard(), getPreviousCard())) {
                        if (mode == 1) { // Single player mode
                            handleSinglePlayerCheckIsSnap();
                            break;
                        } else if (mode == 2) { // multiplayer mode
                            handleMultiplayerCheckIsSnap(scanner, currentPlayer);
                            break;
                        }
                    }
                }
                setPreviousCard(getCurrentCard());
            }
        }
    }
}

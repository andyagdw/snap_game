package org.example;

import card.Snap;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Snap newSnapGame = new Snap("New Game");
            String errorText = "Please enter a number that's greater than 0";
            System.out.print("Please enter your name to start: ");
            String userName;
            while (true) {
                userName = scanner.nextLine().trim();
                if (userName.isEmpty()) {
                    System.out.print("Please enter a name to continue: ");
                    continue;
                }
                break;
            }
            newSnapGame.displayGameInstructions(userName);
            int numOfTimesToShuffleDeck;
            while (true) {
                try {
                    numOfTimesToShuffleDeck = newSnapGame.getNumOfTimesToShuffleDeck(scanner);
                    scanner.nextLine();
                    if (numOfTimesToShuffleDeck <= 0) {
                        System.out.println(errorText);
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(errorText);
                    scanner.next();
                }
            }

            for (int i = 0; i < numOfTimesToShuffleDeck; i++) {
                newSnapGame.shuffleDeck();
            }
            System.out.printf("Deck has been shuffled %d time(s)\n\n", numOfTimesToShuffleDeck);
            newSnapGame.playGame(scanner);
            System.out.print("Play again? y or n: ");
            String userWantsToPlayAgain = scanner.nextLine();
            if (!userWantsToPlayAgain.equals("y")) {
                break;
            }
        }
    }
}
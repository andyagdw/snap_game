package org.example;

import card.Snap;
import player.Player;
import utils.MyUtils;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        String errorText = "Please enter a number that's greater than 0";
        while (true) {
            Snap newSnapGame = new Snap("New Game");
            Player.listOfPlayers.clear(); // Remove previous players
            System.out.println("Please enter a mode you would like to play:\n1. Single Player\n2. Multi-Player");
            int mode;
            try {
                mode = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Please enter a number that's greater than 0");
                continue;
            }
            scanner.nextLine();
            if (mode == 1) {
    //            SINGLE PLAYER MODE
                System.out.print("Please enter your name to start: ");
                String userName = MyUtils.getPlayerName(scanner);
                new Player(userName);
                System.out.println("Number of players: " + Player.listOfPlayers.size()); // Debugging
                System.out.println("Deck Size: " + newSnapGame.getDeck().size()); // Debugging
                newSnapGame.displayGameInstructions(userName);
                int numOfTimesToShuffleDeck = MyUtils.getNumOfTimesToShuffleDeck(newSnapGame, errorText, scanner);

                for (int i = 0; i < numOfTimesToShuffleDeck; i++) {
                    newSnapGame.shuffleDeck();
                }
                System.out.printf("Deck has been shuffled %d time(s)\n\n", numOfTimesToShuffleDeck);
                newSnapGame.playGame(scanner, mode);
    //            End SINGLE PLAYER MODE
            } else if (mode == 2) {
//                MULTI-PLAYER MODE
                System.out.print("Please enter a name for player 1: ");
                String player1UserName = MyUtils.getPlayerName(scanner);
                System.out.print("Please enter a name for player 2: ");
                String player2UserName = MyUtils.getPlayerName(scanner);

                new Player(player1UserName);
                new Player(player2UserName);
                newSnapGame.displayGameInstructions(player1UserName, player2UserName);
                System.out.println("Number of players: " + Player.listOfPlayers.size()); // Debugging
                System.out.println("Deck Size: " + newSnapGame.getDeck().size()); // Debugging

                int numOfTimesToShuffleDeck = MyUtils.getNumOfTimesToShuffleDeck(newSnapGame, errorText, scanner);

                for (int i = 0; i < numOfTimesToShuffleDeck; i++) {
                    newSnapGame.shuffleDeck();
                }
                System.out.printf("Deck has been shuffled %d time(s)\n\n", numOfTimesToShuffleDeck);
                newSnapGame.playGame(scanner, mode);
//                END MULTI-PLAYER MODE
            } else {
                continue; // Integers that are not 1 and 2
            }
            Player.setPlayerIndex(0); // Reset player index
            System.out.print("Play again? y or n: ");
            String userWantsToPlayAgain = scanner.nextLine();
            if (!userWantsToPlayAgain.equals("y")) {
                break;
            }
        }
    }
}
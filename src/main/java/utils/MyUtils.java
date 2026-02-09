package utils;

import card.Snap;

import java.util.Scanner;

public class MyUtils {
    public static String getPlayerName(Scanner scanner) {
        while (true) {
            String userName = scanner.nextLine().trim();
            if (userName.isEmpty()) {
                System.out.print("Please enter a name to continue: ");
                continue;
            }
            return userName;
        }
    }

    public static int getNumOfTimesToShuffleDeck(Snap newSnapGame, String errorText, Scanner scanner) {        int numOfTimesToShuffleDeck;
        while (true) {
            try {
                numOfTimesToShuffleDeck = newSnapGame.getNumOfTimesToShuffleDeck(scanner);
                scanner.nextLine();
                if (numOfTimesToShuffleDeck <= 0) {
                    System.out.println(errorText);
                    continue;
                }
                return numOfTimesToShuffleDeck;
            } catch (Exception e) {
                System.out.println(errorText);
                scanner.next();
            }
        }
    }
}

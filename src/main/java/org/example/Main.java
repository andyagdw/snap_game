package org.example;

import card.Card;
import card.CardGame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        CardGame cardGame = new CardGame("Game 1");
        System.out.println(cardGame.getDeck().size());

        Card myCard = new Card("♥", "2", 4);
    }
}

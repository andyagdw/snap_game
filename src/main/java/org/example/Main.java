package org.example;

import card.CardGame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        CardGame cardGame = new CardGame("Game 1");
        cardGame.shuffleDeck();
        cardGame.sortDeckIntoSuits();
        System.out.println(cardGame.getDeck());
    }
}

package player;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    public static List<Player> listOfPlayers = new ArrayList<>();
    public static int playerIndex = 0;

    public Player(String playerName) {
        this.playerName = playerName;
        listOfPlayers.add(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public static Player getNextPlayer() {
        if (playerIndex == listOfPlayers.size()) {
            playerIndex = 0;
        }
        return listOfPlayers.get(playerIndex++);
    }

    public static void setPlayerIndex(int playerIndex) {
        Player.playerIndex = playerIndex;
    }

    @Override
    public String toString() {
        return getPlayerName();
    }
}

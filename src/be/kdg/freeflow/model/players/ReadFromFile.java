package be.kdg.freeflow.model.players;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.FreeFlowException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    private static Player player;
    private static FreeFlow game;

    public static FreeFlow read() {
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"))) {
            String user = is.readLine();
            while (user != null) {
                String[] userIter = user.split(";");
                if (player.getUsername().equals(userIter[1])) {
                    for (int i = 3; i < userIter.length; i++) {
                        game.listLevels().get(i - 3).setHighscore(Integer.parseInt(userIter[i]));
                    }
                    return game;
                }
                user = is.readLine();
            }
        } catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
        return null;
    }

    public static void setPlayer(Player player) {
        ReadFromFile.player = player;
    }

    public static void setGame() {
        ReadFromFile.game = new FreeFlow(player);
    }
}

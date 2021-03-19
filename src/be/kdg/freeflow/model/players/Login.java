package be.kdg.freeflow.model.players;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.players.Player;

import java.io.*;

public class Login {
    private FreeFlow game;
    Player player;

    public boolean login(String name, String password) {
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"))) {
            String user = is.readLine();
            while (user != null) {
                String[] userIter = user.split(";");
                if (name.equals(userIter[1]) && password.equals(userIter[2])) {
                    this.player = new Player(name, password);
                    is.close();
                    return true;
                }
                user = is.readLine();
            }
        }
        catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
        return false;
    }

    public void register(String username, String password, String repeatPassword) throws FreeFlowException {
        this.player = new Player(username, password, repeatPassword);
    }

    public void logout() {
        this.player = null;
    }

    /**
     * Display highscore menu
     */




    /**
     * Everything called by startGame()
     */


    public FreeFlow getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}

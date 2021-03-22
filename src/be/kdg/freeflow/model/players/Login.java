package be.kdg.freeflow.model.players;

import be.kdg.freeflow.model.FreeFlowException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
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
        } catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
        return false;
    }

    public void register(String username, String password, String repeatPassword) throws FreeFlowException {
        if (username.length() < 2)
            throw new FreeFlowException("Gebruikersnaam moet minstens\n 2 karakters lang zijn.");
        else if (password.length() < 4)
            throw new FreeFlowException("Wachtwoord moet minstens\n 4 karakters lang zijn.");
        else
            this.player = new Player(username, password, repeatPassword);
    }

    public void logout() {
        this.player = null;
    }

    public Player getPlayer() {
        return player;
    }
}

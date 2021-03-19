package be.kdg.freeflow.model.players;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.FreeFlowException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class Player {
    private String username;
    private String password;

    public Player(String username, String password, String repeatPassword) {
        if (nietGebruikt(username))
                this.username = username;
        else
            throw new FreeFlowException("Gebruikersnaam is al in gebruik");

        if (password.equals(repeatPassword)) {
            this.password = password;
        } else {
            throw new FreeFlowException("Wachtwoorden komen niet overeen");
        }
        SaveToFile.setLevels(new FreeFlow(this).listLevels());
        SaveToFile.addPlayer(this);
    }

    public Player(String username, String pass) {
        this.username = username;
        this.password = pass;
    }

    public void newWachtwoord(String oudWachtwoord, String nieuwWachtwoord, String herhaalNieuwWachtwoord) {
        if (password.equals(oudWachtwoord)) {
            if (nieuwWachtwoord.equals(herhaalNieuwWachtwoord))
                password = nieuwWachtwoord;
            else throw new InputMismatchException("Wachtwoorden komen niet overeen!");
        } else
            throw new InputMismatchException("Het oude wachtwoord klopt niet!");
    }

    private boolean nietGebruikt(String name) {
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"));) {
            String line = is.readLine();
            while (line != null) {
                String readName = line.split(";")[1];
                if (readName.equals(name)) {
                    return false;
                }
                line = is.readLine();
            }
        }
        catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return String.format("%s;%s", username, password);
    }
}
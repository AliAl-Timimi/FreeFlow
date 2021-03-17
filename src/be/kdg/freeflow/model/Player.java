package be.kdg.freeflow.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private String username;
    private String wachtwoord;

    public Player(String username, String wachtwoord, String herhaalWachtwoord) {

        if (nietGebruikt(username))
                this.username = username;
        else
            throw new FreeFlowException("gebruikersnaam is al in gebruik");

        if (wachtwoord.equals(herhaalWachtwoord)) {
            this.wachtwoord = wachtwoord;
        } else {
            throw new FreeFlowException("Wachtwoorden komen niet overeen");
        }
    }

    public Player(String username, String pass) {
        this.username = username;
        this.wachtwoord = pass;
    }

    public void newWachtwoord(String oudWachtwoord, String nieuwWachtwoord, String herhaalNieuwWachtwoord) {
        if (wachtwoord.equals(oudWachtwoord)) {
            if (nieuwWachtwoord.equals(herhaalNieuwWachtwoord))
                wachtwoord = nieuwWachtwoord;
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

    public String toString() {
        return String.format("%s;%s", username, wachtwoord);
    }
}
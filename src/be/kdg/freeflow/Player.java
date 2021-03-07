package be.kdg.freeflow;

import java.util.InputMismatchException;

public class Player {
    private String naam;
    private String email;
    private String wachtwoord;

    public Player(String naam, String email, String wachtwoord, String herhaalWachtwoord) {
        this.naam = naam;
        boolean geldig = false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@')
                for (int j = 0; j < email.substring(i).length(); j++) {
                    if (email.substring(i).charAt(j) == '.') {
                        geldig = true;
                        break;
                    }
                }
        }
        if (geldig)
            this.email = email;
        else
            throw new InputMismatchException("Ongelidige email ingegeven!");

        if (wachtwoord.equals(herhaalWachtwoord)) {
            this.wachtwoord = wachtwoord;
        } else {
            throw new InputMismatchException("Wachtwoorden komen niet overeen!");
        }
    }

    public void newWachtwoord(String oudWachtwoord, String nieuwWachtwoord, String herhaalNieuwWachtwoord) {
        if (wachtwoord.equals(oudWachtwoord)) {
            if (nieuwWachtwoord.equals(herhaalNieuwWachtwoord))
                wachtwoord = nieuwWachtwoord;
            else throw new InputMismatchException("Wachtwoorden komen niet overeen!");
        } else
            throw new InputMismatchException("Het oude wachtwoord klopt niet!");
    }

    public String toString() {
        return String.format("%s,%s,%s", naam, email, wachtwoord);
    }
}
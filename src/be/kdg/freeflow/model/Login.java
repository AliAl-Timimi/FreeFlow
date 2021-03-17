package be.kdg.freeflow.model;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Login {
    private FreeFlow game;

    ArrayList<Player> players = new ArrayList<>();
    Player player;

    public boolean login(String name, String password) {
        String username;
        String pass;
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"));) {
            String user = is.readLine();
            while (user != null) {
                String[] userIter = user.split(";");
                if (name.equals(userIter[1]) && password.equals(userIter[2])) {
                    username = name;
                    pass = password;
                    this.player = new Player(username, pass);
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

    public void register(String mail, String password, String repeatPassword) throws FreeFlowException {
        this.player = new Player(mail, password, repeatPassword);
    }

    public void logout() {
        this.player = null;
    }

    private void startGame() {
        int lvl = levelMenu();
        game.chooseLevel(lvl);
    }

    /**
     * Display highscore menu
     */
    private void highscores() {
        List<Level> levels = game.listLevels();
        StringBuilder stringBuilder = new StringBuilder();
        int total = 0;
        int max = levels.size() * 3;
        for (Level level : levels) {
            stringBuilder.append(level.toString()).append("\n");
            total += level.getHighscore();
        }
        System.out.printf("%n%10s%s%n%n", " ", "SCORES");
        System.out.printf("Totale score: %d/%d%n", total, max);
        System.out.println("Highscore per level:");
        System.out.println(stringBuilder.toString());
        System.out.print("Druk op enter om terug te gaan.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * Create new player
     */
    private Player newPlayer() {
        Player player = null;
        Scanner sc = new Scanner(System.in);
        String naam, email;
        String ww, herhaalww;

        boolean repeat;
        do {
            try {
                System.out.printf("%n%15s%s%n%n", " ", "REGISTRATIE");
                System.out.print("Geef een gebruikersnaam in: ");
                System.out.print("Geef uw e-mail in: ");
                email = sc.nextLine();
                System.out.print("Geef een wachtwoord in: ");
                ww = sc.nextLine();
                player = new Player(email, ww);
                repeat = false;
            } catch (InputMismatchException e) {
                repeat = true;
                System.out.println(e.getMessage());
            }
        } while (repeat);
        return player;
    }

    /**
     * Change settings:
     * setStyle & setSoundEffects cycle through all possible settings
     */




    private void changePassword() {
        String oldPassword, newPassword, repeatNewPassword;
        Scanner sc = new Scanner(System.in);
        boolean repeat;
        do {
            try {
                System.out.printf("%n%15s%s%n%n", " ", "SETTINGS");
                System.out.println("Geef 0 in om deze actie te annuleren\n");
                System.out.print("Geef het oud wachtwoord in: ");
                oldPassword = sc.nextLine();
                if (oldPassword.equals("0"))
                    return;
                System.out.print("Geef het nieuw wachtwoord in: ");
                newPassword = sc.nextLine();
                if (newPassword.equals("0"))
                    return;
                System.out.print("herhaal het nieuwe wachtwoord in: ");
                repeatNewPassword = sc.nextLine();
                if (repeatNewPassword.equals("0"))
                    return;
                player.newWachtwoord(oldPassword, newPassword, repeatNewPassword);
                repeat = false;
            } catch (InputMismatchException e) {
                repeat = true;
                System.out.println(e.getMessage());
            }
        } while (repeat);
    }

    /**
     * Everything called by startGame()
     */
    private int levelMenu() { // Print level list to choose
        List<Level> levels = game.listLevels();
        int page = 0;
        String choice = null;
        boolean done = false;
        while (!done) {
            System.out.printf("%n%15s%s%n%n", " ", "LEVELS");
            int lvlReached = 0;
            for (int i = 10 * page; i < 10 * page + 10 && i < levels.size(); i++) {
                if (i != 0 && levels.get(i - 1).getHighscore() == 0)
                    lvlReached++;

                System.out.print(levels.get(i).toString());
                if (lvlReached == 0)
                    System.out.println();
                else
                    System.out.println(" \uD83D\uDD12");

            }
            System.out.println("\nvorige pagina (B)   volgende pagina (N)");
            System.out.print("Maak uw keuze: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.next();
            switch (choice) {
                case "B":
                    if (page > 0)
                        page--;
                    break;
                case "N":
                    if (10 * page <= levels.size())
                        page++;
                    break;
                default: {
                    int lvl = Integer.parseInt(choice);
                    if (lvl < 1 || lvl > levels.size()) {
                        System.out.println("Geen geldige keuze");
                    } else if (lvl == 1 || levels.get(lvl - 2).getHighscore() > 0)
                        done = true;
                    else
                        System.out.println("Level nog niet open");
                }
            }
        }
        return Integer.parseInt(choice);
    }

}

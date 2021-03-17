package be.kdg.freeflow.model;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Login {
    private FreeFlow game;
    Player player;

    public boolean login(String name, String password) {
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"));) {
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


    private void startGame() {
        int lvl = levelMenu();
        game.chooseLevel(lvl);
    }

    /**
     * Display highscore menu
     */




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

    public FreeFlow getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}

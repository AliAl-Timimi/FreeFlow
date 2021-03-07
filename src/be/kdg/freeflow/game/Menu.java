package be.kdg.freeflow.game;

import be.kdg.freeflow.Player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private FreeFlow game;
    private boolean soundEffects = true;
    private int style = 0;
    Player player;


    public void showMenu()  {
        Scanner sc = new Scanner(System.in);
        int input;
        boolean stop = false;

        showLoginScreen();
        do {
            try {
                // mainmenu met 4 mogeliijke keuzes
                showMainMenu();
                System.out.print("Uw keuze: ");
                try {
                    input = sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    throw new IllegalArgumentException("Geen geldige input!");
                }
                // Input
                switch (input) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        settings();
                        break;
                    case 3:
                        highscores();
                        break;
                    case 4:
                        help();
                        break;
                    case 0:
                        stop = true;
                        break;
                    default:
                        sc.nextLine();
                        throw new IllegalArgumentException("Geen geldige input");
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (!stop);
    }

    /**
     * Registration player will repeat until a valid email and password is given
     * To-do: Check for unique name & email with external file
     **/
    private void showLoginScreen() {
        System.out.printf("%10sWELKOM BIJ FREEFLOW%n%n%10smaak een keuze%n", " ", " ");
        System.out.println("Maak een nieuw account aan (1)");
        System.out.println("Log in op een bestaand account (2)");
        System.out.print("\nMaak uw keuze: ");
        Scanner sc = new Scanner(System.in);

        while (true) {
            int choice = 9;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Geen geldige keuze");
            }

            switch (choice) {
                case 1: {
                    boolean stop = false;
                    do { // registratie speler
                        try {
                            player = newPlayer();
                            stop = true;
                            game = new FreeFlow(player);
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            stop = false;
                        }
                    } while (!stop);
                    return;
                }
                case 2: {
                    System.out.println("Login:");
                    game = new FreeFlow(player);
                    return;
                }
                default:
                    System.out.println("Geen geldige keuze");
            }
        }
    }

    /**
     * Display main menu
     */
    private void showMainMenu() {
        System.out.println("\n");
        System.out.printf("%10sWELKOM BIJ FREEFLOW%n%n%10smaak een keuze%n", " ", " ");
        System.out.println("Start Game (1)        Open Settings(2)");
        System.out.println("Show highscores (3)   Help (4)");
    }

    private void startGame() {
        int lvl = levelMenu();
        game.chooseLevel(lvl);
    }



    /**
     * Display settings menu
     */
    private void settings() {
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        do {
            System.out.printf("%n%15s%s%n%n", " ", "SETTINGS");
            System.out.println("Doorloop setting");
            System.out.println("Terug naar main menu (0)");
            System.out.printf("Stijl  (1):  %s%n", Style.values()[style]);
            System.out.printf("Geluid (2):  %s%n", soundEffects ? "Aan" : "Uit");
            System.out.println("Wachtwoord veranderen (3)");

            System.out.print("\nUw keuze: ");
            int choice = -1;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Geen geldige invoer!");
            }
            switch (choice) {
                case 1:
                    setStyle();
                    break;
                case 2:
                    setSoundEffects();
                    break;
                case 3:
                    changePassword();
                    break;
                case 0:
                    repeat = false;
                    break;
                default:
                    sc.nextLine();
                    System.out.println("Geen mogelijke keuze!");
            }
        } while (repeat);
    }

    /**
     * Display Help screen inside the menu
     */
    private void help() {
        System.out.printf("%15s%s%n%n", " ", "HELP");
        System.out.println("Het doel van het spel is om alle bollen (letters)\n" +
                "die dezelfde kleur hebben met elkaar te verbinden\n" +
                "zonder dat deze verbindingen over een andere verbinding gaan\n" +
                "Wanneer elke bol met de andere bol van dezelfde kleur verbonden is\n" +
                "is dat level gedaan. Probeer elk level met zo weinig mogelijk moves\n" +
                "af te maken om de hoogst mogelijke score te behalen!\n\n" +
                "Een move maken gebeurt door een vak te kiezen waar een bol\n" +
                "of een lijn staat waarvan je verder wilt werken. Dan moet de move\n" +
                "ingegeven worden in een sequentie van letters.\nEen voorbeeld:\n" +
                "RRRRUUUULLD zal dan 4 keer naar rechts gaan, 4 keer naar boven\n" +
                "2 keer naar links en dan 1 keer naar onder.\n\n");
        System.out.print("Druk op enter om terug te gaan.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
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
                naam = sc.nextLine();
                System.out.print("Geef uw e-mail in: ");
                email = sc.nextLine();
                System.out.print("Geef een wachtwoord in: ");
                ww = sc.nextLine();
                System.out.print("Herhaal uw wachtwoord: ");
                herhaalww = sc.nextLine();
                player = new Player(naam, email, ww, herhaalww);
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
    private void setStyle() {
        if (style == 4)
            style = 0;
        else style++;
    }

    private void setSoundEffects() {
        soundEffects = !soundEffects;
    }

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

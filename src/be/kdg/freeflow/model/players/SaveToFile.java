package be.kdg.freeflow.model.players;

import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.lvlbuild.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveToFile {
    private static Player player;
    private static List<Level> levels;
    private static int currentPlayer;

    public static void save(List<Level> levels) {
        List<String[]> users;
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"))) {
            users = new ArrayList<>();
            String user = is.readLine();
            while (user != null) {
                users.add(user.split(";"));
                user = is.readLine();
            }
        } catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }

        // Current user
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i)[1].equals(player.getUsername())) {
                currentPlayer = i;
                break;
            }
        }

        // change user info
        List<String> string = Arrays.asList(users.get(currentPlayer));


        for (int i = 3; i < levels.size() + 3; i++) {
            if (string.size() > i)
                string.set(i, String.valueOf(levels.get(i - 3).getHighscore()));
        }

        users.remove(currentPlayer);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("resources/data/users.csv")))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : string) {
                stringBuilder.append(s).append(";");
            }
            stringBuilder.append("\n");
            pw.write(stringBuilder.toString());
            for (String[] user : users) {
                StringBuilder stringBuilder1 = new StringBuilder();
                for (String s : user) {
                    stringBuilder1.append(s).append(";");
                }
                stringBuilder1.append("\n");
                pw.write(stringBuilder1.toString());
            }
        } catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
    }

    public static void addPlayer(Player player) {
        List<String[]> users;
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/users.csv"))) {
            users = new ArrayList<>();
            String user = is.readLine();
            while (user != null) {
                users.add(user.split(";"));
                user = is.readLine();
            }
        } catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("resources/data/users.csv")))) {
            for (String[] user : users) {
                StringBuilder stringBuilder1 = new StringBuilder();
                for (String s : user) {
                    stringBuilder1.append(s).append(";");
                }
                stringBuilder1.append("\n");
                pw.write(stringBuilder1.toString());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1").append(";");
            stringBuilder.append(player.getUsername()).append(";");
            stringBuilder.append(player.getPassword()).append(";");
            for (int i = 0; i < levels.size(); i++) {
                stringBuilder.append(levels.get(i).getHighscore()).append(";");
            }
            stringBuilder.append("\n");
            pw.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new FreeFlowException("Gebruiker gegevens niet gevonden.");
        }
    }

    public static void setPlayer(Player player) {
        SaveToFile.player = player;
    }

    public static void setLevels(List<Level> levels) {
        SaveToFile.levels = levels;
    }
}

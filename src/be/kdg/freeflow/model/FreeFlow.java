package be.kdg.freeflow.model;

import be.kdg.freeflow.model.lvlbuild.Grid;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.players.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FreeFlow {
    private final List<Level> levels;
    private final Player player;

    public FreeFlow(Player player) {
        this.player = player;
        this.levels = new ArrayList<>();
        createLevels();
    }

    public Level chooseLevel(int i) {
        return levels.get(i - 1);
    }

    public void createLevels() {
        //Start reading external file
        Scanner sc;
        int lvlNumber = 1;
        String line;
        try {
            sc = new Scanner(new File("resources/data/levels.csv"));
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                StringBuilder stringBuilder = new StringBuilder();
                while (line.charAt(0) != ';') {
                    line = line.replaceAll(";", "");
                    stringBuilder.append(line).append("\n");
                    line = sc.nextLine();
                }

                String[] lines = stringBuilder.toString().split("\n");
                Grid solution = new Grid(lines.length);
                int k = 0;
                for (String line1 : lines) {
                    for (int j = 0; j < line1.length(); j++) {
                        solution.fillCell(k, j, line1.charAt(j) + "");
                    }
                    k++;
                }

                String[] emptyLines = new String[lines.length];
                Grid empty = new Grid(lines.length);
                for (int i = 0; i < lines.length; i++) {
                    emptyLines[i] = lines[i].replaceAll("[a-z]", " ");

                }
                int i = 0;
                for (String line1 : emptyLines) {
                    for (int j = 0; j < line1.length(); j++) {
                        String letter = line1.charAt(j) + "";
                        if (!letter.equals(" "))
                            empty.fillCell(i, j, letter);
                    }
                    i++;
                }
                int size = lines.length;
                levels.add(new Level(lvlNumber++, size, empty, solution));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Bestand ontbreekt: levels.csv");
        }
    }

    public List<Level> listLevels() {
        return levels;
    }

    public Player getPlayer() {
        return player;
    }
}
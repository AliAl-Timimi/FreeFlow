package be.kdg.freeflow.game;

import java.util.Locale;
import java.util.Scanner;

public class Level implements Comparable<Level> {
    private int levelNummer;
    private int size;
    private int highscore;
    private Grid empty;
    private Grid solution;
    private int moves;

    public Level(int levelNummer, int size, Grid empty, Grid solution) {
        this.levelNummer = levelNummer;
        this.size = size;
        this.highscore = 0;
        this.empty = empty;
        this.solution = solution;
        this.moves = 0;
    }

    public int getSize() {
        return size;
    }

    public int getLevelNummer() {
        return levelNummer;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public Grid getEmpty() {
        return empty;
    }

    public Grid getSolution() {
        return solution;
    }

    public int getMoves() {
        return moves;
    }

    public void speel() {
        Scanner keyboard = new Scanner(System.in);
        while (!isGedaan()) {
            boolean repeat = true;
            char[] moveArray;

            int row;
            int col;
            boolean cont;
            do {
                System.out.print(empty);
                cont = true;
                System.out.print("Geef de startpositie (rij- en kolomnummer gescheiden door een spatie): ");
                try {
                    String input = keyboard.nextLine().toLowerCase();
                    int spaces = 0;
                    for (int i = 0; i < input.length(); i++) {
                        if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != ' ' || spaces > 1)
                            throw new IllegalArgumentException();
                        if (input.charAt(i) == ' ')
                            spaces++;
                    }
                    String[] inArray =  input.split(" ");
                    row = Integer.parseInt(inArray[0]);
                    col = Integer.parseInt(inArray[1]);
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    row = -1;
                    col = -1;
                }
                if (col >= getSize() || row >= getSize() || col < 0 || row < 0 ||
                        (empty.getGrid()[row][col].getBall() == null && empty.getGrid()[row][col].getPipe() == null)) {
                    System.out.println("Ongeldige startpositie");
                    cont = false;
                }
            } while (!cont);


            String color;
            if (empty.getGrid()[row][col].getBall() != null)
                color = empty.getGrid()[row][col].getBall().getColor().toString();
            else
                color = empty.getGrid()[row][col].getPipe().getColor().toString();


            do {
                System.out.println("Doe een move: ");
                String move = keyboard.nextLine().toLowerCase();
                moveArray = move.toCharArray();
                for (int i = 0; i < moveArray.length; i++) {
                    if (moveArray[i] != 'l' && moveArray[i] != 'r' && moveArray[i] != 'u' && moveArray[i] != 'd') {
                        System.out.println("Ongeldige input");
                        repeat = true;
                    } else {
                        repeat = false;
                    }
                }
            } while (repeat);
            for (int i = 0; i < moveArray.length; i++) {
                switch (moveArray[i]) {
                    case 'l':
                        col--;
                        break;
                    case 'r':
                        col++;
                        break;
                    case 'u':
                        row--;
                        break;
                    case 'd':
                        row++;
                        break;
                }
                if (empty.getGrid()[row][col].isEmpty()) {
                    empty.fillCell(row, col, color);
                } else {
                    i = moveArray.length;
                }
                moves++;
            }
        }
        if (getMoves() == empty.minMoves())
            setHighscore(3);
        else if (getMoves() + 1 == empty.minMoves())
            setHighscore(2);
        else if (getMoves() + 2 >= empty.minMoves())
            setHighscore(1);
    }

    public boolean isGedaan() {
        return getEmpty().equals(getSolution());
    }

    public String toString() {
        return String.format("Level %d: %dx%d %d★", getLevelNummer(), getSize(), getSize(), getHighscore());
    }

    @Override
    public int compareTo(Level o) {
        return getLevelNummer() - o.getLevelNummer();
    }
}

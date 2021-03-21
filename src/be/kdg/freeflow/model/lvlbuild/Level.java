package be.kdg.freeflow.model.lvlbuild;

import be.kdg.freeflow.model.flow.Color;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int levelNummer;
    private final int size;
    private int highscore;
    private final Grid reset;
    private Grid empty;
    private final Grid solution;
    private int moves;
    private String color;

    public Level(int levelNummer, int size, Grid empty, Grid solution) {
        this.levelNummer = levelNummer;
        this.size = size;
        this.highscore = 0;
        this.empty = empty;
        this.reset = empty;
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

    public void reset() {
        empty = reset;
        moves = 0;
    }

    public int getMoves() {
        return moves;
    }

    private int selectedRow;
    private int selectedColumn;

    public void setSelectedCell(int column, int row) {
        this.selectedColumn = column;
        this.selectedRow = row;
    }

    public void clearSelectedCell() {
        this.selectedColumn = -1;
        this.selectedRow = -1;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColor(Color color) {
        this.color = color.toString();
    }

    public boolean cellEmpty(int row, int col) {
        return empty.getGrid()[row][col].isEmpty();
    }

    private List<Character> moveArray = new ArrayList<>();

    public void clearMoveArray() {
        moveArray.clear();
    }

    public void addMove(char c) {
        moveArray.add(c);
    }

    public void delMove() {
        moveArray.remove(moveArray.size() - 1);
    }

    public String getColor() {
        return color;
    }

    public void writeToLevel() {
        if (moveArray.size() != 0) {
            int col = selectedColumn;
            int row = selectedRow;
            for (int i = 0; i < moveArray.size(); i++) {
                switch (moveArray.get(i)) {
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
                    empty.fillCell(row, col, getColor());
                } else {
                    i = moveArray.size();
                }
            }
            moves++;
        }
        isGameFinished();
    }

    public void createScore() {
        if (getMoves() == empty.minMoves())
            setHighscore(3);
        else if (getMoves() + 1 == empty.minMoves())
            setHighscore(2);
        else if (getMoves() + 2 >= empty.minMoves())
            setHighscore(1);
    }

    public boolean isGameFinished() {
        return getEmpty().equals(getSolution());
    }

    public String toString() {
        return String.format("Level %d: %dx%d %d★", getLevelNummer(), getSize(), getSize(), getHighscore());
    }

    public String starScore() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = getHighscore(); i > 0; i--) {
            stringBuilder.append("★").append(" ");
        }
        return stringBuilder.toString();
    }
}

package be.kdg.freeflow.model.lvlbuild;

import be.kdg.freeflow.model.flow.Color;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int LEVELNUMMER;
    private final int SIZE;
    private int highscore;
    private Grid reset;
    private Grid empty;
    private final Grid SOLUTION;
    private int moves;
    private Color color;

    public Level(int levelNummer, int size, Grid empty, Grid solution) {
        this.LEVELNUMMER = levelNummer;
        this.SIZE = size;
        this.highscore = 0;
        this.empty = empty;
        this.reset = empty;
        this.SOLUTION = solution;
        this.moves = 0;
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getLEVELNUMMER() {
        return LEVELNUMMER;
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

    public Grid getSOLUTION() {
        return SOLUTION;
    }

    public void reset() {
        empty = new Grid(reset);
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
        this.color = color;
    }

    private List<Character> moveArray = new ArrayList<>();

    public void clearMoveArray() {
        moveArray.clear();
    }

    public void addMove(char c) {
        moveArray.add(c);
    }

    public Color getColor() {
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
                    empty.fillCell(row, col, getColor().toString());
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
        else if (getMoves() == empty.minMoves() + 1)
            setHighscore(2);
        else if (getMoves() >= empty.minMoves() + 2)
            setHighscore(1);
    }

    public boolean isGameFinished() {
        return getEmpty().equals(getSOLUTION());
    }

    public String toString() {
        return String.format("Level %d: %dx%d %d★", getLEVELNUMMER(), getSIZE(), getSIZE(), getHighscore());
    }

    public String starScore() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = getHighscore(); i > 0; i--) {
            stringBuilder.append("★").append(" ");
        }
        return stringBuilder.toString();
    }

    public void resetColor(Color color) {
        for (int i = 0; i < getSIZE(); i++) {
            for (int j = 0; j < getSIZE(); j++) {
                if (getEmpty().getGrid()[i][j].getPipe() != null && getEmpty().getGrid()[i][j].getPipe().getColor() == color) {
                    getEmpty().getGrid()[i][j].clearPipe();
                }
            }
        }
    }
}

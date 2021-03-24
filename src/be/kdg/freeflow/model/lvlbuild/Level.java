package be.kdg.freeflow.model.lvlbuild;

import be.kdg.freeflow.model.flow.Color;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int LEVELNUMMER;
    private final int SIZE;
    private final Grid SOLUTION;
    private int highscore;
    //private final Grid reset;
    private Grid empty;
    private int moves;
    private Color color;
    private int selectedRow;
    private int selectedColumn;
    private final List<Character> moveArray = new ArrayList<>();

    public Level(int levelNummer, int size, Grid empty, Grid solution) {
        this.LEVELNUMMER = levelNummer;
        this.SIZE = size;
        this.highscore = 0;
        this.empty = empty;
        this.SOLUTION = solution;
        this.moves = 0;
    }

    public void reset() {
        for (Color value : Color.values()) {
            resetColor(value);
        }
        //empty = new Grid(reset);
        moves = 0;
        setSelectedCell(-1, -1);
    }

    public void setSelectedCell(int column, int row) {
        this.selectedColumn = column;
        this.selectedRow = row;
        if (selectedColumn == -1 || selectedRow == -1) {
            setSelectedColor(null);
        }
        else {
            if (getEmpty().getGrid()[selectedRow][selectedColumn].getBall() != null)
                setSelectedColor(getEmpty().getGrid()[selectedRow][selectedColumn].getBall().getColor());
            else if (getEmpty().getGrid()[selectedRow][selectedColumn].getPipe() != null)
                setSelectedColor(getEmpty().getGrid()[selectedRow][selectedColumn].getPipe().getColor());
        }
    }

    private void setSelectedColor(Color color) {
        this.color = color;
    }

    public void clearMoveArray() {
        moveArray.clear();
    }

    public void addMove(char c) {
        moveArray.add(c);
    }

    public void writeToLevel() {
        if (moveArray.size() != 0) {
            int col = selectedColumn;
            int row = selectedRow;
            int prevCol = -1;
            int prevRow = -1;
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
                if (empty.getGrid()[row][col].isEmpty() && getColor() != null) {
                    empty.fillCell(row, col, getColor().toString());
                    empty.getGrid()[row][col].getPipe().addLine();
                } else {
                    i = moveArray.size();
                }
                if (prevCol != -1) {
                    getEmpty().getGrid()[prevRow][prevCol].getPipe().addLine();
                }
                prevCol = col;
                prevRow = row;
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
        return String.format("Level %d: %dx%d %d★", getLevelnummer(), getSIZE(), getSIZE(), getHighscore());
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
                if (getEmpty().getGrid()[i][j].getBall() != null && getEmpty().getGrid()[i][j].getBall().getColor() == color) {
                    getEmpty().getGrid()[i][j].getBall().setLijnAanwezig(false);
                }
            }
        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getLevelnummer() {
        return LEVELNUMMER;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        if (highscore > this.highscore)
            this.highscore = highscore;
    }

    public int getMoves() {
        return moves;
    }

    public Grid getEmpty() {
        return empty;
    }

    public Grid getSOLUTION() {
        return SOLUTION;
    }

    public Color getColor() {
        return color;
    }
}

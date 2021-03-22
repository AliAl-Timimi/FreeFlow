package be.kdg.freeflow.model.lvlbuild;

import be.kdg.freeflow.model.flow.Color;

public class Grid {
    private final Cell[][] grid;

    public Grid(int size) {

        this.grid = new Cell[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Cell();
            }

        }
    }

    public Grid(Grid a) {
        this(a.getGrid().length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (a.getGrid()[i][j].getBall() != null)
                    grid[i][j] = a.getGrid()[i][j];
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int minMoves() {
        return ballCount() / 2;
    }

    public int ballCount() {
        int count = 0;
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.getBall() != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public void fillCell(int x, int y, String color) {
        if (!color.equals(color.toLowerCase())) {
            grid[x][y].addBall(Color.valueOf(color.toUpperCase()));
        } else
            grid[x][y].addPipe(Color.valueOf(color.toUpperCase()));
    }

    public boolean equals(Grid o) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!grid[i][j].toString().equals(o.getGrid()[i][j].toString()))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            if (i == 0) {
                stringBuilder.append("   ");
                for (int k = 0; k < grid.length; k++)
                    stringBuilder.append(k).append(" ");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                for (int j = 0; j < 2 + grid.length * 2; j++)
                    stringBuilder.append("-");
                stringBuilder.append("\n");
            }
            for (int j = 0; j < grid.length; j++) {
                if (j == 0)
                    stringBuilder.append(String.format("%2d", i)).append("|");
                stringBuilder.append(grid[i][j].toString()).append(" ");

            }
            stringBuilder.append("|");
            stringBuilder.append("\n");
        }
        stringBuilder.append("  ");
        for (int j = 0; j < 2 + grid.length * 2; j++)
            stringBuilder.append("-");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}

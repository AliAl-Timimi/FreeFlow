package be.kdg.freeflow.model;

import java.util.List;

public class Scores {

    public Scores() {
    }

    public String highscores(FreeFlow game) {
        List<Level> levels = game.listLevels();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int total = 0;
        int max = levels.size() * 3;
        for (Level level : levels) {
            stringBuilder.append(level.toString()).append("\n");
            total += level.getHighscore();
        }
        stringBuilder2.append(String.format("Totale score: %d/%d%n", total, max));
        stringBuilder2.append("Highscore per level\n");
        stringBuilder2.append(stringBuilder.toString());
        return stringBuilder2.toString();
    }
}

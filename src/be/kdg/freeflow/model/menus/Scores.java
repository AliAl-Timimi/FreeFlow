package be.kdg.freeflow.model.menus;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.players.ReadFromFile;
import java.util.List;

public class Scores {
    private List<Level> levels;
    private FreeFlow game;

    public Scores(FreeFlow game) {
        levels = game.listLevels();
        this.game = game;
        setScores();
    }

    public String highscores() {
        setScores();
        levels = game.listLevels();
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

    private void setScores() {
        ReadFromFile.setPlayer(game.getPlayer());
        game = ReadFromFile.read();
    }
}

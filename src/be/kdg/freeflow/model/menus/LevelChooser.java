package be.kdg.freeflow.model.menus;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelChooser {
    private List<Level> levels;
    private int page = 0;
    private int lvlReached = 0;

    public LevelChooser(FreeFlow game) {
        this.levels = game.listLevels();
    }

    public List<Level> levelMenu() {
        List<Level> displayLevels = new ArrayList<>();
        for (int i = 5 * page; i < 5 * page + 5 && i < levels.size(); i++) {
            displayLevels.add(levels.get(i));
        }

        if (lvlReached == 0)
            System.out.println();
        else
            System.out.println(" \uD83D\uDD12");

        return displayLevels;
    }


    public boolean isLevelUnlocked(int lvl) {
        return lvl-1 <= lvlReached;
    }

    public void setLevelReached() {
        for (int i = 0; i < levels.size(); i++) {
            if (i != 0 && levels.get(i - 1).getHighscore() != 0)
                lvlReached++;
        }
    }

    public void prevPage() {
        if (page > 0)
            page--;
    }

    public void nextPage() {
        if (5 * page <= levels.size())
            page++;
    }
}

package be.kdg.freeflow.model.menus;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;
import java.util.ArrayList;
import java.util.List;

public class LevelChooser {
    private final FreeFlow game;
    private final List<Level> levels;
    private final int maxPage;
    private int page = 0;

    private int currentImage;
    public LevelChooser(FreeFlow game) {
        this.game = game;
        this.levels = this.game.listLevels();
        this.maxPage = (int) Math.ceil(levels.size() / 5.0) - 1;
    }

    public List<Level> levelMenu() {
        List<Level> displayLevels = new ArrayList<>();
        for (int i = 5 * page; i < 5 * page + 5 && i < levels.size(); i++) {
            displayLevels.add(levels.get(i));
        }
        return displayLevels;
    }

    public boolean isLevelUnlocked(int lvl) {
        if (lvl > 1)
            return levels.get(lvl - 2).getHighscore() > 0;
        else
            return true;
    }

    public boolean prevPage() {
        if (page > 0) {
            page--;
            return true;
        }
        return false;
    }

    public boolean nextPage() {
        if (page < maxPage) {
            page++;
            return true;
        }
        return false;
    }
}

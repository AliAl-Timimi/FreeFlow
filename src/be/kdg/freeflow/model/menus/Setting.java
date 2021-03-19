package be.kdg.freeflow.model.menus;

public class Setting {
    private int style = 0;
    private boolean soundEffects = true;


    public void cycleStyle() {
        if (style >= 2)
            style = 0;
        else style++;
    }

    public void setSoundEffects() {
        soundEffects = !soundEffects;
    }

    public Style getStyle() {
        return Style.values()[style];
    }

    public boolean getSoundEffects() {
        return soundEffects;
    }
}

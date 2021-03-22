package be.kdg.freeflow.model.menus;

import be.kdg.freeflow.model.FreeFlowException;
import java.io.*;

public class Setting {
    private int style = 0;
    private boolean soundEffects = true;

    public Setting() {
        try (BufferedReader is = new BufferedReader(new FileReader("resources/data/settings.txt"))) {
            String[] settings = is.readLine().split(";");
            this.setStyle(Integer.parseInt(settings[0]));
            setSoundEffects(settings[1].equalsIgnoreCase("true"));

        } catch (IOException e) {
            throw new FreeFlowException("Geen settings file");
        }
    }

    public void cycleStyle() {
        if (style >= Style.values().length - 1)
            style = 0;
        else style++;
    }

    public void cycleSoundEffects() {
        soundEffects = !soundEffects;
    }

    public Style getStyle() {
        return Style.values()[style];
    }

    public void setStyle(int i) {
        this.style = i;
    }

    public boolean getSoundEffects() {
        return soundEffects;
    }

    public void setSoundEffects(Boolean bool) {
        soundEffects = bool;
    }

    public void save() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("resources/data/settings.txt")))) {
            pw.write(style + ";" + getSoundEffects());
        } catch (IOException e) {
            throw new FreeFlowException("Settings file niet gevonden.");
        }
    }
}

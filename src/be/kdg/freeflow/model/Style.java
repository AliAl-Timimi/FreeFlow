package be.kdg.freeflow.model;

public enum Style {
    CLASSIC,
    DARK_MODE,
    LIGHT;

    /*
    classic: standaard
    dark: zwarte achtergrond, geel, rood
    light: witte achtergrond, blauw
    holliday: rood, groen, blauw
    winter_chill: blauw, wit
     */

    @Override
    public String toString() {
        String name = name().toLowerCase().replaceAll("_", " ");
        return String.format("%s", name.substring(0,1).toUpperCase() + name.substring(1));
    }
}

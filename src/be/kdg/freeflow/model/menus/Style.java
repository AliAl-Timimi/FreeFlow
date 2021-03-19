package be.kdg.freeflow.model.menus;

public enum Style {
    CLASSIC("/stylesheets/default.css"),
    DARK_MODE("/stylesheets/darkmode.css"),
    LIGHT("/stylesheets/lightmode.css");

    /*
    classic: standaard
    dark: zwarte achtergrond, geel, rood
    light: witte achtergrond, blauw
    holliday: rood, groen, blauw
    winter_chill: blauw, wit
     */

    private final String s;

    Style(String s) {
        this.s=s;
    }

    @Override
    public String toString() {
        String name = name().toLowerCase().replaceAll("_", " ");
        return String.format("%s", name.substring(0,1).toUpperCase() + name.substring(1));
    }

    public String getS() {
        return s;
    }
}

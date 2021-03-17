package be.kdg.freeflow.model;

public enum Style {
    CLASSIC,
    DARK_MODE,
    LIGHT,
    HOLLIDAY,
    WINTER_CHILL;

    @Override
    public String toString() {
        String name = name().toLowerCase().replaceAll("_", " ");
        return String.format("%s", name.substring(0,1).toUpperCase() + name.substring(1));
    }
}

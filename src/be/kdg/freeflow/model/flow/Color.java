package be.kdg.freeflow.model.flow;

public enum Color {
    R(javafx.scene.paint.Color.RED),
    Y(javafx.scene.paint.Color.YELLOW),
    G(javafx.scene.paint.Color.GREEN),
    L(javafx.scene.paint.Color.LIME),
    T(javafx.scene.paint.Color.DARKGREEN),
    B(javafx.scene.paint.Color.BLUE),
    C(javafx.scene.paint.Color.CYAN),
    D(javafx.scene.paint.Color.DARKBLUE),
    O(javafx.scene.paint.Color.ORANGE),
    N(javafx.scene.paint.Color.BROWN),
    W(javafx.scene.paint.Color.WHITE),
    P(javafx.scene.paint.Color.PINK),
    U(javafx.scene.paint.Color.PURPLE);

    private final javafx.scene.paint.Color color;

    Color(javafx.scene.paint.Color color) {
        this.color = color;
    }

    public javafx.scene.paint.Color getColor() {
        return color;
    }

    public String getC() {
        return name();
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}


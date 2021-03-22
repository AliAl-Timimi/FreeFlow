package be.kdg.freeflow.model.flow;


public class Ball {
    private final Color color;

    public Ball(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.getC();
    }

    public Color getColor() {
        return color;
    }

}

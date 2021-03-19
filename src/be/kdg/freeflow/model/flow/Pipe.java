package be.kdg.freeflow.model.flow;


public class Pipe {
    private Color color;

    public Pipe(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return getColor().toString();
    }

    public Color getColor() {
        return color;
    }

}

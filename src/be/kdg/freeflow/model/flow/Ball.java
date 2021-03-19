package be.kdg.freeflow.model.flow;


public class Ball {
    private Color color;
    private boolean lineCreated = false;

    public Ball(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.getC();
    }

    public boolean getLineCreated() {
        return lineCreated;
    }

    public void createLine() {
        lineCreated = true;
    }

    public Color getColor() {
        return color;
    }

}

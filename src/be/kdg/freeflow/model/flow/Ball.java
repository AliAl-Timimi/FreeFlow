package be.kdg.freeflow.model.flow;


public class Ball {
    private final Color color;
    private boolean lijnAanwezig;

    public Ball(Color color) {
        this.color = color;
        this.lijnAanwezig = false;
    }

    public boolean isLijnAanwezig() {
        return lijnAanwezig;
    }

    public void setLijnAanwezig(boolean lijnAanwezig) {
        this.lijnAanwezig = lijnAanwezig;
    }

    @Override
    public String toString() {
        return color.getC();
    }

    public Color getColor() {
        return color;
    }

}
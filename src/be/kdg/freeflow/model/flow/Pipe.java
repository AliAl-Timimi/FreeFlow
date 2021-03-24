package be.kdg.freeflow.model.flow;


public class Pipe {
    private final Color color;
    private int lines;

    public Pipe(Color color) {
        this.color = color;
        this.lines = 0;
    }

    public void addLine() {
        lines++;
    }

    public int getLines() {
        return lines;
    }

    public void clearLines() {
        this.lines = 0;
    }

    @Override
    public String toString() {
        return getColor().toString();
    }

    public Color getColor() {
        return color;
    }

}
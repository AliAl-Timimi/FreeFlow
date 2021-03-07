package be.kdg.freeflow.game;

import be.kdg.freeflow.flow.Ball;
import be.kdg.freeflow.flow.Color;
import be.kdg.freeflow.flow.Pipe;

public class Cell {
    private Ball ball = null;
    private Pipe pipe = null;

    public boolean isEmpty() {
        return pipe == null && ball == null;
    }

    public void empty() {
        this.ball = null;
        this.pipe = null;
    }

    @Override
    public String toString() {
        if (ball != null)
            return ball.toString();
        else if (pipe != null)
            return pipe.toString();
        else
            return " ";
    }

    public void addBall(Color color) {
        this.ball = new Ball(color);
    }

    public Ball getBall() {
        return ball;
    }

    public void addPipe(Color color) {
        this.pipe = new Pipe(color);
    }

    public Pipe getPipe() {
        return pipe;
    }
}

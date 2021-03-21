package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.lvlbuild.Level;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class GameView<height> extends GridPane {
    private GridPane gamePane;

    Level level;
    private Button back;
    private Label levelMarker;
    private Label moves;
    private final double width = 500.0;
    private final double height = 500.0;


    public GameView(Level level) {
        this.level=level;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        back = new Button("back");
        levelMarker = new Label();
        moves = new Label();
        gamePane = new GridPane();
        gamePane.setMinWidth(width);
        gamePane.setMinHeight(height);
    }
    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        this.add(back, 0, 0);
        this.add(levelMarker, 1 ,0);
        this.add(moves, 2, 0);
        this.add(gamePane, 0,1,3,1);

        GridPane.setHalignment(back, HPos.LEFT);
        GridPane.setHalignment(levelMarker, HPos.CENTER);
        GridPane.setHalignment(moves, HPos.RIGHT);
        GridPane.setHalignment(gamePane, HPos.CENTER);

        ColumnConstraints column1 = new ColumnConstraints(166);
        for (int i =0; i < 2; i++) {
            this.getColumnConstraints().add(column1);
        }

        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(500);
        this.getRowConstraints().addAll(row1, row2);

        levelMarker.setId("levelmarker");
        moves.setId("moves");
        ColumnConstraints column = new ColumnConstraints(width/level.getSize());
        for (int i =0; i < level.getSize(); i++) {
            gamePane.getColumnConstraints().add(column);
        }
        RowConstraints row = new RowConstraints(width/level.getSize());
        for (int i =0; i < level.getSize(); i++) {
            gamePane.getRowConstraints().add(row);
        }

        gamePane.setAlignment(Pos.CENTER);
        gamePane.setGridLinesVisible(true);

        gamePane.setId("game_background");
    }

    public Level getLevel() {
        return level;
    }

    public Button getBack() {
        return back;
    }

    public Label getLevelMarker() {
        return levelMarker;
    }

    public Label getMoves() {
        return moves;
    }

    public GridPane getGamePane() {
        return gamePane;
    }

    public void fillBalls(int column, int row, be.kdg.freeflow.model.flow.Color color) {
        Circle circle = new Circle(width/level.getSize(), height/level.getSize(),((width/level.getSize())/2)-5);
        circle.setFill(color.getColor());
        gamePane.add(circle, column, row);
        GridPane.setHalignment(circle, HPos.CENTER);
        GridPane.setValignment(circle, VPos.CENTER);
    }

}
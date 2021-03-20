package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.lvlbuild.Level;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class GameView extends GridPane {
    private Canvas canvas;
    private final double tileWidth;
    private final double tileHeight;
    //private GridPane gamePane;

    Level level;
    private Button back;
    private Label levelMarker;
    private Label moves;


    private static final double CANVAS_WIDTH= 500;
    private static final double CANVAS_HEIGHT= 500;

    public GameView(Level level) {
        this.level=level;
        this.tileWidth = CANVAS_WIDTH / level.getSize();
        this.tileHeight = CANVAS_HEIGHT / level.getSize();

        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        back = new Button("back");
        levelMarker = new Label();
        moves = new Label();
        canvas = new Canvas();
        /**/
        //gamePane=new GridPane();
    }
    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        this.add(back, 0, 0);
        this.add(levelMarker, 1 ,0);
        this.add(moves, 2, 0);
        this.add(canvas, 0,1,3,1);

        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setHalignment(levelMarker, HPos.CENTER);
        GridPane.setHalignment(moves, HPos.CENTER);

        ColumnConstraints colum1 = new ColumnConstraints(200);
        ColumnConstraints colum2 = new ColumnConstraints(200);
        ColumnConstraints colum3 = new ColumnConstraints(200);
        this.getColumnConstraints().addAll(colum1, colum2, colum3);


        this.setGridLinesVisible(true);

        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(500);
        this.getRowConstraints().addAll(row1, row2);

        levelMarker.setId("levelmarker");
        moves.setId("moves");

    }

    /*public Canvas getCanvas() {
        return canvas;
    }

    public double getTileWidth() {
        return tileWidth;
    }

    public double getTileHeight() {
        return tileHeight;
    }*/

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

    /*public static double getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static double getCanvasHeight() {
        return CANVAS_HEIGHT;
    }*/
}
package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.lvlbuild.Level;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;


public class GameView<height> extends GridPane {
    //private Canvas canvas;
    //private final double tileWidth;
    //private final double tileHeight;
    private GridPane gamePane;

    Level level;
    private Button back;
    private Label levelMarker;
    private Label moves;
    private final double width = 500.0;
    private final double height = 500.0;

    //private static final Color SELECTED_TILE_COLOR = Color.rgb(0, 0, 80);

    //private static final double CANVAS_WIDTH= 500;
    //private static final double CANVAS_HEIGHT= 500;

    public GameView(Level level) {
        this.level=level;
        //this.tileWidth = CANVAS_WIDTH / level.getSize();
        //this.tileHeight = CANVAS_HEIGHT / level.getSize();

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
        //canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        /**/
        //gamePane=new GridPane();
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

    /*
    public Canvas getCanvas() {
        return canvas;
    }

     */

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

    /*
    void displayCurrent(int currentHoverColumn, int currentHoverRow, int currentSelectedColumn, int currentSelectedRow) {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKBLUE);
        gc.fillRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);

        if (currentHoverColumn != -1 && currentHoverRow != -1) {
            gc.setFill(Color.BLUE);
            gc.fillRect(currentHoverColumn * tileWidth, currentHoverRow * tileHeight, tileWidth, tileHeight);
        }

        if (currentSelectedColumn != -1 && currentSelectedRow != -1) {
            gc.setStroke(SELECTED_TILE_COLOR);
            gc.setLineWidth(20.0);
            gc.strokeRect(currentSelectedColumn * tileWidth + 10.0, currentSelectedRow * tileHeight + 10.0, tileWidth - 20.0, tileHeight - 20.0);
        }
    }

*/
    public void fillBalls(int column, int row, be.kdg.freeflow.model.flow.Color color) {
        Circle circle = new Circle(width/level.getSize(), height/level.getSize(),((width/level.getSize())/2)-5);
        circle.setFill(color.getColor());
        gamePane.add(circle, column, row);
        GridPane.setHalignment(circle, HPos.CENTER);
        GridPane.setValignment(circle, VPos.CENTER);
    }

}
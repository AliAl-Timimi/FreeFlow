package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.lvlbuild.Cell;
import be.kdg.freeflow.model.lvlbuild.Level;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;


public class GameView extends GridPane {
    private final double WIDTH = 500.0;
    private final double HEIGHT = 500.0;
    private GridPane gamePane;
    private final String[][] grid;
    private final Level level;
    private Button back;
    private Label levelMarker;
    private Label moves;
    private Button restart;

    public GameView(Level level) {
        this.level = level;
        this.initialiseNodes();
        this.layoutNodes();
        this.grid = new String[level.getSIZE()][level.getSIZE()];
        for (int i = 0; i < level.getSIZE(); i++) {
            for (int j = 0; j < level.getSIZE(); j++) {
                grid[i][j] = level.getEmpty().getGrid()[i][j].toString();
            }
        }
        fillLevel();
    }

    private void initialiseNodes() {
        back = new Button("Terug");
        levelMarker = new Label();
        moves = new Label();
        restart = new Button("Restart");
        gamePane = new GridPane();
        gamePane.setMinWidth(WIDTH);
        gamePane.setMinHeight(HEIGHT);
    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        this.add(back, 0, 0);
        this.add(levelMarker, 1, 0);
        this.add(moves, 2, 0);
        this.add(restart, 1, 2);

        GridPane.setHalignment(back, HPos.LEFT);
        GridPane.setHalignment(levelMarker, HPos.CENTER);
        GridPane.setHalignment(moves, HPos.RIGHT);
        GridPane.setHalignment(restart, HPos.CENTER);
        GridPane.setHalignment(gamePane, HPos.CENTER);

        ColumnConstraints column1 = new ColumnConstraints(166);
        for (int i = 0; i < 2; i++) {
            this.getColumnConstraints().add(column1);
        }
        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(500);
        this.getRowConstraints().addAll(row1, row2);
        ColumnConstraints column = new ColumnConstraints(WIDTH / level.getSIZE());
        for (int i = 0; i < level.getSIZE(); i++) {
            gamePane.getColumnConstraints().add(column);
        }
        RowConstraints row = new RowConstraints(WIDTH / level.getSIZE());
        for (int i = 0; i < level.getSIZE(); i++) {
            gamePane.getRowConstraints().add(row);
        }

        levelMarker.setId("levelmarker");
        moves.setId("moves");
        setMargin(restart, new Insets(15, 0, 0, 0));

        this.add(gamePane, 0, 1, 3, 1);
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

    public Button getRestart() {
        return restart;
    }

    public void clearGrid() {
        Node node = gamePane.getChildren().get(0);
        gamePane.getChildren().clear();
        gamePane.getChildren().add(0, node);
        for (int i = 0; i < level.getSIZE(); i++) {
            for (int j = 0; j < level.getSIZE(); j++) {
                grid[i][j] = level.getEmpty().getGrid()[i][j].toString();
            }
        }
        fillAllBalls();
        fillAllPipes();
    }

    public void fillAllPipes() {
        for (int i = 0; i < level.getSIZE(); i++) {
            for (int j = 0; j < level.getSIZE(); j++) {
                if (level.getEmpty().getGrid()[i][j].getPipe() != null) {
                    Circle circle = new Circle(WIDTH / level.getSIZE(), HEIGHT / level.getSIZE(), ((WIDTH / level.getSIZE()) / 2) - 15);
                    circle.setFill(level.getEmpty().getGrid()[i][j].getPipe().getColor().getColor());
                    gamePane.add(circle, j, i);
                    GridPane.setHalignment(circle, HPos.CENTER);
                    GridPane.setValignment(circle, VPos.CENTER);
                    this.grid[i][j] = level.getEmpty().getGrid()[i][j].getPipe().getColor().getColor().toString();
                }
            }
        }
    }

    public void fillPipe(int column, int row, be.kdg.freeflow.model.flow.Color color) {
        try {
            if (level.getEmpty().getGrid()[column][row].isEmpty() && color != null) {
                Circle circle = new Circle(WIDTH / level.getSIZE(), HEIGHT / level.getSIZE(), ((WIDTH / level.getSIZE()) / 2) - 15);
                circle.setFill(color.getColor());
                gamePane.add(circle, row, column);
                GridPane.setHalignment(circle, HPos.CENTER);
                GridPane.setValignment(circle, VPos.CENTER);
                this.grid[column][row] = color.toString();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FreeFlowException("index out of bounds");
        }
    }

    public void fillBalls(int column, int row, be.kdg.freeflow.model.flow.Color color) {
        Circle circle = new Circle(WIDTH / level.getSIZE(), HEIGHT / level.getSIZE(), ((WIDTH / level.getSIZE()) / 2) - 5);
        circle.setFill(color.getColor());
        gamePane.add(circle, column, row);
        GridPane.setHalignment(circle, HPos.CENTER);
        GridPane.setValignment(circle, VPos.CENTER);
    }

    private void fillAllBalls() {
        for (int i = 0; i < level.getSIZE(); i++) {
            for (int j = 0; j < level.getSIZE(); j++) {
                if (level.getEmpty().getGrid()[i][j].getBall() != null) {
                    fillBalls(j, i, level.getEmpty().getGrid()[i][j].getBall().getColor());
                }
            }
        }
    }

    private void fillLevel() {
        Cell[][] game = level.getEmpty().getGrid();
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j].getBall() != null) {
                    this.fillBalls(j, i, game[i][j].getBall().getColor());
                }
            }

        }
    }
}
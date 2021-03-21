package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.flow.Color;
import be.kdg.freeflow.model.lvlbuild.Cell;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.players.SaveToFile;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;
import be.kdg.freeflow.view.popup.PopupPresenter;
import be.kdg.freeflow.view.popup.PopupView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePresenter {
    private final Level model;
    private final GameView view;
    private final LevelChooserView levelChooserView;
    private Setting setting;
    private FreeFlow game;

    private static int currentHoverColumn;
    private static int currentHoverRow;

    private static int currentSelectedColumn;
    private static int currentSelectedRow;

    private static int prevHoverColumn;
    private static int prevHoverRow;

    private boolean selected;
    private Color color;

    public GamePresenter(Level model, GameView view, LevelChooserView levelChooserView, Setting setting, FreeFlow game) {
        this.model = model;
        this.view = view;
        this.levelChooserView = levelChooserView;
        this.setting = setting;
        this.game = game;
        updateMoves();
        setLevelText();
        fillLevel();
        addEventHandlers();
    }


    private int translateXToColumn(final double x) {
        final double width = this.view.getGamePane().getWidth();
        final int columnResult = (int) (x / width * model.getSize());
        if (columnResult >= 0 && columnResult < model.getSize()) {
            return columnResult;
        } else {
            return -1;
        }
    }

    private int translateYToRow(final double y) {
        final double height = this.view.getGamePane().getHeight();
        final int rowResult = (int) (y / height * model.getSize());
        if (rowResult >= 0 && rowResult < model.getSize()) {
            return rowResult;
        } else {
            return -1;
        }
    }

    private void fillLevel() {
        Cell[][] game = model.getEmpty().getGrid();
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j].getBall() != null) {
                    view.fillBalls(j, i, game[i][j].getBall().getColor());
                }
            }

        }
    }

    private void setLevelText() {
        view.getLevelMarker().setText(String.format("Level: %d", model.getLevelNummer()));
    }

    private void updateMoves() {
        view.getMoves().setText(String.format("Moves: %d/%d", model.getMoves(), model.getEmpty().minMoves()));
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(event -> updateViewToLevelChooser());
        GridPane gridPane = view.getGamePane();

        gridPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GamePresenter.currentHoverColumn = translateXToColumn(mouseEvent.getX());
                GamePresenter.currentHoverRow = translateYToRow(mouseEvent.getY());
                if (model.isGameFinished())
                    gameFinished();

            }
        });

        gridPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GamePresenter.currentHoverColumn = translateXToColumn(mouseEvent.getX());
                GamePresenter.currentHoverRow = translateYToRow(mouseEvent.getY());
                if (selected) {
                    if (prevHoverColumn != currentHoverColumn) {
                        switch (prevHoverColumn - currentHoverColumn) {
                            case 1: model.addMove('l');
                            case -1: model.addMove('r');
                        }
                        prevHoverColumn = currentHoverColumn;
                    }
                    else if (prevHoverRow != currentHoverRow) {
                        switch (prevHoverRow - currentHoverRow) {
                            case 1: model.addMove('d');
                            case -1: model.addMove('u');
                        }
                        prevHoverRow = currentHoverRow;
                    }
                }
            }
        });

        gridPane.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selected = true;
                prevHoverColumn = currentHoverColumn;
                prevHoverRow = currentHoverRow;
                currentSelectedRow = currentHoverRow;
                currentSelectedColumn = currentHoverColumn;
                model.setSelectedCell(currentSelectedRow ,currentSelectedColumn );
                System.out.println(1);
            }
        });

        gridPane.setOnMouseDragExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                model.writeToLevel();
                updateMoves();
                selected = false;
                System.out.print(model.getEmpty());
                model.clearMoveArray();
            }
        });
        if (model.isGameFinished())
            showPupWindow();
    }

    private void updateViewToLevelChooser() {
        view.getScene().setRoot(levelChooserView);
    }

    private void gameFinished() {
        model.createScore();
        game.chooseLevel(model.getLevelNummer()).setHighscore(model.getHighscore());
        SaveToFile.save(game.listLevels());
        showPupWindow();
    }

    private void showPupWindow() {
        PopupView pop = new PopupView();
        PopupPresenter presenter = new PopupPresenter(model, pop, view, levelChooserView, setting, game);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pop));
        popupStage.setTitle(String.format("Level %d complete!", model.getLevelNummer()));
        pop.getScene().getStylesheets().add(setting.getStyle().getS());
        popupStage.setHeight(300);
        popupStage.setWidth(325);
        popupStage.show();
    }
}
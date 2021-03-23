package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.flow.Color;
import be.kdg.freeflow.model.lvlbuild.Cell;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.model.players.SaveToFile;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;
import be.kdg.freeflow.view.popup.PopupPresenter;
import be.kdg.freeflow.view.popup.PopupView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePresenter {
    private static int currentHoverColumn;
    private static int currentHoverRow;
    private static int currentSelectedColumn;
    private static int currentSelectedRow;
    private static int prevHoverColumn;
    private static int prevHoverRow;
    private final Level model;
    private final GameView view;
    private final LevelChooserView levelChooserView;
    private final Setting setting;
    private final FreeFlow game;
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
        final int columnResult = (int) (x / width * model.getSIZE());
        if (columnResult >= 0 && columnResult < model.getSIZE()) {
            return columnResult;
        } else {
            return -1;
        }
    }

    private int translateYToRow(final double y) {
        final double height = this.view.getGamePane().getHeight();
        final int rowResult = (int) (y / height * model.getSIZE());
        if (rowResult >= 0 && rowResult < model.getSIZE()) {
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
        view.getLevelMarker().setText(String.format("Level %d", model.getLevelnummer()));
    }

    private void updateMoves() {
        view.getMoves().setText(String.format("Moves: %d/%d", model.getMoves(), model.getEmpty().minMoves()));
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                model.reset();
                updateViewToLevelChooser();
            }
        });
        GridPane gridPane = view.getGamePane();

        gridPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GamePresenter.currentHoverColumn = translateXToColumn(mouseEvent.getX());
                GamePresenter.currentHoverRow = translateYToRow(mouseEvent.getY());
                if (model.isGameFinished())
                    gameFinished();
                updateMoves();

            }
        });

        gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    if (model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall().getColor() != null) {
                        model.resetColor(model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall().getColor());
                        view.clearGrid();
                    }
                }
            }
        });


        gridPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GamePresenter.currentHoverColumn = translateXToColumn(mouseEvent.getX());
                GamePresenter.currentHoverRow = translateYToRow(mouseEvent.getY());
                try {
                    if (model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall() != null) {
                        if (model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall().getColor() != model.getColor()) {
                            color = null;
                            selected = false;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }


                if (currentHoverRow < 0 || currentHoverColumn < 0 || currentHoverRow > model.getSIZE() || currentHoverColumn > model.getSIZE()) {
                    try {
                        model.clearMoveArray();
                        model.getEmpty().getGrid()[currentSelectedRow][currentSelectedColumn].getBall().setLijnAanwezig(false);
                        selected = false;
                        color = null;
                        view.clearGrid();
                    } catch (NullPointerException ignored) {
                    }
                }

                if (selected) {
                    if (prevHoverColumn != currentHoverColumn) {
                        switch (prevHoverColumn - currentHoverColumn) {
                            case 1:
                                model.addMove('l');
                                break;
                            case -1:
                                model.addMove('r');
                                break;
                        }
                        prevHoverColumn = currentHoverColumn;
                    } else if (prevHoverRow != currentHoverRow) {
                        switch (prevHoverRow - currentHoverRow) {
                            case 1:
                                model.addMove('u');
                                break;
                            case -1:
                                model.addMove('d');
                                break;
                        }
                        prevHoverRow = currentHoverRow;
                    }
                    try {
                        if (model.getEmpty().getGrid()[currentSelectedRow][currentSelectedColumn].getPipe() != null &&
                                model.getEmpty().getGrid()[currentSelectedRow][currentSelectedColumn].getPipe().getLines() < 2 ||
                                model.getEmpty().getGrid()[currentSelectedRow][currentSelectedColumn].getBall() != null) {
                            view.fillPipe(currentHoverRow, currentHoverColumn, color);
                        }
                    } catch (FreeFlowException e) {
                        color = null;
                    }
                }
            }
        });

        gridPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Sound.play();
                selected = true;
                prevHoverColumn = currentHoverColumn;
                prevHoverRow = currentHoverRow;
                currentSelectedRow = currentHoverRow;
                currentSelectedColumn = currentHoverColumn;
                if (model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall() != null &&
                        !model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall().isLijnAanwezig() ||
                        model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getPipe() != null &&
                                model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getPipe().getLines() < 2) {
                    try {
                        model.setSelectedCell(currentSelectedColumn, currentSelectedRow);
                        model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall().setLijnAanwezig(true);
                        color = model.getColor();
                    } catch (NullPointerException ignored) {
                    }
                } else {
                    model.setSelectedColor(null);
                }
            }
        });

        gridPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall() != null)
                        model.getEmpty().getGrid()[currentHoverRow][currentHoverColumn].getBall().setLijnAanwezig(true);
                    if( model.getEmpty().getGrid()[currentSelectedRow][currentSelectedColumn].getPipe() != null &&
                            model.getEmpty().getGrid()[currentSelectedRow][currentSelectedColumn].getPipe().getLines() == 2) {
                        model.clearMoveArray();
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                Sound.play();
                model.writeToLevel();
                view.clearGrid();
                updateMoves();
                selected = false;
                model.clearMoveArray();
                model.setSelectedColor(null);
            }
        });

        view.getRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                updateToCurrentGameView();
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
        game.chooseLevel(model.getLevelnummer()).setHighscore(model.getHighscore());
        SaveToFile.save(game.listLevels());
        showPupWindow();
    }

    private void showPupWindow() {
        PopupView pop = new PopupView();
        PopupPresenter presenter = new PopupPresenter(model, pop, view, levelChooserView, setting, game);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pop));
        popupStage.setTitle(String.format("Level %d complete!", model.getLevelnummer()));
        popupStage.getIcons().add(new Image("/pictures/icon.png"));
        pop.getScene().getStylesheets().add(setting.getStyle().getS());
        popupStage.setHeight(300);
        popupStage.setWidth(325);
        popupStage.show();
    }

    private void updateToCurrentGameView() {
        model.reset();
        GameView gameView = new GameView(game.chooseLevel(model.getLevelnummer()));
        GamePresenter presenter = new GamePresenter(game.chooseLevel(model.getLevelnummer()), gameView, levelChooserView, setting, game);
        this.view.getScene().setRoot(gameView);
    }
}
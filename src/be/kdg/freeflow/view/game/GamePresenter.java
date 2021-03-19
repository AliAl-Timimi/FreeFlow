package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.view.game.GameView;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class GamePresenter {
    private Level model;
    private GameView view;
    private LevelChooserView levelChooserView;

    public GamePresenter(Level model, GameView view, LevelChooserView levelChooserView) {
        this.model = model;
        this.view = view;
        this.levelChooserView = levelChooserView;
        updateMoves();
        setLevelText();
        addEventHandlers();
    }

    private void setLevelText() {
        view.getLevelMarker().setText(String.format("Level: %d", model.getLevelNummer()));
    }

    private void updateMoves() {
        view.getMoves().setText(String.format("Moves: %d", model.getMoves()));
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateViewToLevelChooser();
            }
        });
    }

    private void updateViewToLevelChooser() {
        view.getScene().setRoot(levelChooserView);
    }

    /* gewerkt in:
    - gamepresenter
    - gameviewer
    - levelchooserpresenter (updateviewtogame)
     */
}
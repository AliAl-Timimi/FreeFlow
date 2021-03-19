package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;

public class GamePresenter {
    private final Level model;
    private final GameView view;
    private final LevelChooserView levelChooserView;

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
        view.getMoves().setText(String.format("Moves: %d/%d", model.getMoves(), model.getEmpty().minMoves()));
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(event -> updateViewToLevelChooser());
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
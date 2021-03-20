package be.kdg.freeflow.view.game;

import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;
import be.kdg.freeflow.view.popup.PopupPresenter;
import be.kdg.freeflow.view.popup.PopupView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePresenter {
    private final Level model;
    private final GameView view;
    private final LevelChooserView levelChooserView;
    private Setting setting;

    public GamePresenter(Level model, GameView view, LevelChooserView levelChooserView, Setting setting) {
        this.model = model;
        this.view = view;
        this.levelChooserView = levelChooserView;
        this.setting = setting;
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

        if (model.isGedaan())
            showPupWindow();
    }

    private void updateViewToLevelChooser() {
        view.getScene().setRoot(levelChooserView);
    }

    private void showPupWindow() {
        PopupView pop = new PopupView();
        PopupPresenter presenter = new PopupPresenter(model, pop);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pop));
        popupStage.setTitle(String.format("Level %d complete!", model.getLevelNummer()));
        pop.getScene().getStylesheets().add(setting.getStyle().getS());
        // pop.setStyle("-fx-background-image: url(/backgrounds/darkthemebackground.jpg)");
        popupStage.setHeight(300);
        popupStage.setWidth(325);
        popupStage.showAndWait();
    }
}
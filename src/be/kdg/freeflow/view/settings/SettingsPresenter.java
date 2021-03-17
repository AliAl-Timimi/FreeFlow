package be.kdg.freeflow.view.settings;

import be.kdg.freeflow.model.Setting;
import be.kdg.freeflow.model.Style;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static be.kdg.freeflow.model.Style.*;

public class SettingsPresenter {
    private Setting setting;
    private SettingsView view;
    private MenuView menuView;


    public SettingsPresenter(Setting setting, SettingsView settingsView, MenuView menuView) {
        this.setting = setting;
        this.view = settingsView;
        this.menuView = menuView;

        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getStyleButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setting.cycleStyle();
                view.getStyleButton().setText(String.format("Stijl: %s",setting.getStyle()));
            }
        });
        view.getSound().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setting.setSoundEffects();
                view.getSound().setText(String.format("SFX: %s",setting.getSoundEffects()?"Aan":"Uit"));
            }
        });
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToMenu();
            }
        });
    }

    private void updateToMenu() {
        view.getScene().setRoot(menuView);
    }
}

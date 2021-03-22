package be.kdg.freeflow.view.settings;

import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SettingsPresenter {
    private final Setting setting;
    private final SettingsView view;
    private final MenuView menuView;


    public SettingsPresenter(Setting setting, SettingsView settingsView, MenuView menuView) {
        this.setting = setting;
        this.view = settingsView;
        this.menuView = menuView;
        view.getStyleButton().setText(String.format("Stijl: %s", setting.getStyle()));
        view.getSound().setText(String.format("SFX: %s", setting.getSoundEffects() ? "Aan" : "Uit"));
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getStyleButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                setting.cycleStyle();
                view.getScene().getStylesheets().clear();
                view.getStyleButton().setText(String.format("Stijl: %s", setting.getStyle()));
                view.getScene().getStylesheets().add(setting.getStyle().getS());
            }
        });
        view.getSound().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                setting.cycleSoundEffects();
                view.getSound().setText(String.format("SFX: %s", setting.getSoundEffects() ? "Aan" : "Uit"));
                if (!setting.getSoundEffects())
                    Sound.setVolume(0);
                else
                    Sound.setVolume(1);
            }
        });
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                setting.save();
                updateToMenu();
            }
        });
    }

    private void updateToMenu() {
        view.getScene().setRoot(menuView);
    }
}

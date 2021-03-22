package be.kdg.freeflow.view.login;

import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.model.players.Login;
import be.kdg.freeflow.view.menu.MenuPresenter;
import be.kdg.freeflow.view.menu.MenuView;
import be.kdg.freeflow.view.register.RegisterPresenter;
import be.kdg.freeflow.view.register.RegisterView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class LoginPresenter {
    Login login;
    LoginView view;
    Setting setting;

    public LoginPresenter(Login login, LoginView view) {
        this.login = login;
        this.view = view;
        this.setting = new Setting();
        this.addEventHandlers();
    }

    public void applySettings() {
        view.getScene().getStylesheets().clear();
        view.getScene().getStylesheets().add(setting.getStyle().getS());
        if (!setting.getSoundEffects())
            Sound.setVolume(0);
    }

    private void addEventHandlers() {
        view.getLogin().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                try {
                    if (!login.login(view.getUsername().getText().toLowerCase(), view.getPassword().getText())) {
                        view.getWrongLogin().setText("Verkeerd gebruikersnaam/wachtwoord.");
                        view.getWrongLogin().setTextFill(Color.RED);
                    } else
                        updateViewToMenu();
                } catch (FreeFlowException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Geen user file");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });

        view.getRegister().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateViewToRegister();
            }
        });
    }

    private void updateViewToMenu() {
        MenuView menuView = new MenuView();
        MenuPresenter menuPresenter = new MenuPresenter(menuView, login, view, setting);
        view.getScene().setRoot(menuView);
    }

    private void updateViewToRegister() {
        RegisterView registerView = new RegisterView();
        RegisterPresenter registerPresenter = new RegisterPresenter(login, registerView, view, setting);
        view.getScene().setRoot(registerView);
    }
}

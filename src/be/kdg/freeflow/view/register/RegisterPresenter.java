package be.kdg.freeflow.view.register;

import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.model.players.Login;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.menu.MenuPresenter;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class RegisterPresenter {
    private final Login login;
    private final RegisterView view;
    private final LoginView loginView;
    private final Setting setting;

    public RegisterPresenter(Login login, RegisterView view, LoginView loginView, Setting setting) {
        this.loginView = loginView;
        this.login = login;
        this.view = view;
        this.setting = setting;

        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateToLogin();
            }
        });

        view.getCreate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                try {
                    login.register(view.getEmail().getText().toLowerCase(), view.getPassword().getText(), view.getRepeatPassword().getText());
                    updateToMenu();
                } catch (FreeFlowException e) {
                    view.getError().setText(e.getMessage());
                    view.getError().setTextFill(Color.RED);
                }
            }
        });
    }

    private void updateToLogin() {
        view.getScene().setRoot(loginView);
    }

    private void updateToMenu() {
        MenuView menuView = new MenuView();
        new MenuPresenter(menuView, login, loginView, setting);
        view.getScene().setRoot(menuView);
    }
}

package be.kdg.freeflow.view.register;

import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.menus.Login;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.menu.MenuPresenter;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class RegisterPresenter {
    private Login login;
    private RegisterView view;
    private LoginView loginView;

    public RegisterPresenter(Login login, RegisterView view, LoginView loginView) {
        this.loginView = loginView;
        this.login = login;
        this.view = view;

        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToLogin();
            }
        });

        view.getCreate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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
        MenuPresenter menuPresenter = new MenuPresenter(menuView, login, loginView);
        view.getScene().setRoot(menuView);
    }
}

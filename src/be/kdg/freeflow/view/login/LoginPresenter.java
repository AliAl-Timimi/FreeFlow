package be.kdg.freeflow.view.login;

import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.Login;
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


    public LoginPresenter(Login login, LoginView view) {
        this.login = login;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getLogin().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if (!login.login(view.getUsername().getText().toLowerCase(), view.getPassword().getText())) {
                        view.getWrongLogin().setText("Verkeerd gebruikersnaam/wachtwoord.");
                        view.getWrongLogin().setTextFill(Color.RED);
                    }
                    else
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
                updateViewToRegister();
            }
        });
    }

    private void updateViewToMenu() {
        MenuView menuView = new MenuView();
        MenuPresenter menuPresenter = new MenuPresenter(menuView, login, view);
        view.getScene().setRoot(menuView);
    }

    private void updateViewToRegister() {
        RegisterView registerView = new RegisterView();
        RegisterPresenter registerPresenter = new RegisterPresenter(login, registerView, view);
        view.getScene().setRoot(registerView);
    }
}

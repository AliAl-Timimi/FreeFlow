package be.kdg.freeflow.view.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginView extends GridPane {
    private Label welkom;
    private Button register;
    private TextField username;
    private PasswordField password;
    private Button login;
    private Label wrongLogin;

    public LoginView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        welkom = new Label("Welkom bij FreeFlow!");
        wrongLogin = new Label();

        register = new Button("Maak een account aan");
        login = new Button("Login");
        username = new TextField();

        password = new PasswordField();


    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);

        this.add(welkom,1, 1);
        this.add(username,1,3);
        this.add(password,1,4);
        this.add(login,1,6);
        this.add(register,1,7);
        this.add(wrongLogin, 1,5);

        login.setPrefWidth(310);
        register.setPrefWidth(310);

        setMargin(wrongLogin,new Insets(0,0,0,10));
        setMargin(welkom, new Insets(10));
        setMargin(username, new Insets(5,10,5,10));
        setMargin(password, new Insets(5,10,5,10));
        setMargin(register, new Insets(5, 10, 5, 10));
        setMargin(login, new Insets(5, 10, 5, 10));

        username.setPromptText("gebruikersnaam");
        password.setPromptText("wachtwoord");

        welkom.setId("titles");
    }

    protected Button getRegister() {
        return register;
    }

    protected TextField getUsername() {
        return username;
    }

    protected TextField getPassword() {
        return password;
    }

    protected Button getLogin() {
        return login;
    }

    public Label getWrongLogin() {
        return wrongLogin;
    }
}

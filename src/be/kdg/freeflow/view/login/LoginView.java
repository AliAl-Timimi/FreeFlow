package be.kdg.freeflow.view.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class LoginView extends GridPane {
    /*private Label welkom;*/
    private TextFlow flow;
    private Text titelp1;
    private Text titelp2;
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
        flow = new TextFlow();
        titelp1 = new Text("Welkom bij ");
        titelp2 = new Text("FreeFlow!");
        flow.getChildren().addAll(titelp1, titelp2);
        /*welkom = new Label("Welkom bij FreeFlow!");*/
        wrongLogin = new Label();

        register = new Button("Maak een account aan");
        login = new Button("Login");
        username = new TextField();

        password = new PasswordField();


    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);

        titelp1.setId("titelp1");
        titelp2.setId("titelp2");
        this.add(flow, 1, 1);
        flow.setTextAlignment(TextAlignment.CENTER);
        /*this.add(welkom,1, 1);*/
        this.add(username, 1, 3);
        this.add(password, 1, 4);
        this.add(login, 1, 6);
        this.add(register, 1, 7);
        this.add(wrongLogin, 1, 5);

        login.setPrefWidth(310);
        register.setPrefWidth(310);

        setMargin(wrongLogin, new Insets(0, 0, 0, 10));
        /*setMargin(welkom, new Insets(10));*/
        setMargin(username, new Insets(5, 10, 5, 10));
        setMargin(password, new Insets(5, 10, 5, 10));
        setMargin(register, new Insets(5, 10, 5, 10));
        setMargin(login, new Insets(5, 10, 5, 10));

        username.setPromptText("gebruikersnaam");
        password.setPromptText("wachtwoord");

        flow.setId("titles");
    }

    public Button getRegister() {
        return register;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public Button getLogin() {
        return login;
    }

    public Label getWrongLogin() {
        return wrongLogin;
    }
}

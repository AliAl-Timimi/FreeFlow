package be.kdg.freeflow.view.register;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class RegisterView extends BorderPane {
    private GridPane grid;
    private Button back;
    private Label title;
    private TextField email;
    private PasswordField password;
    private PasswordField repeatPassword;
    private Label error;
    private Button create;

    public RegisterView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        grid = new GridPane();
        back = new Button("Terug");
        title = new Label("Registratie");
        email = new TextField();
        password = new PasswordField();
        repeatPassword = new PasswordField();
        error = new Label();
        create = new Button("Account aanmaken");
    }

    private void layoutNodes() {
        this.setLeft(back);
        this.setCenter(grid);

        grid.setAlignment(Pos.CENTER);
        grid.add(title, 1, 1);
        grid.add(email, 1, 2);
        grid.add(password, 1, 3);
        grid.add(repeatPassword, 1, 4);
        grid.add(error, 1, 5);
        grid.add(create, 1, 6);

        setMargin(back, new Insets(5));
        GridPane.setMargin(title, new Insets(10, 0, 10, 0));
        GridPane.setMargin(email, new Insets(5, 0, 5, 0));
        GridPane.setMargin(password, new Insets(5, 0, 5, 0));
        GridPane.setMargin(repeatPassword, new Insets(5, 0, 5, 0));
        GridPane.setMargin(create, new Insets(10, 0, 0, 0));

        create.setPrefWidth(200);

        email.setPromptText("gebruikersnaam");
        password.setPromptText("wachtwoord");
        repeatPassword.setPromptText("herhaal wachtwoord");


        title.setId("titles");
    }

    public Button getBack() {
        return back;
    }

    public TextField getEmail() {
        return email;
    }

    public PasswordField getPassword() {
        return password;
    }

    public PasswordField getRepeatPassword() {
        return repeatPassword;
    }

    public Label getError() {
        return error;
    }

    public Button getCreate() {
        return create;
    }
}


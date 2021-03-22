package be.kdg.freeflow.view.register;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class RegisterView extends GridPane {
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
        back = new Button("Terug");
        title = new Label("Registratie");
        email = new TextField();
        password = new PasswordField();
        repeatPassword = new PasswordField();
        error = new Label();
        create = new Button("Account aanmaken");
    }

    private void layoutNodes() {

        this.setAlignment(Pos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);

        this.add(back, 0, 0);
        this.add(title, 1, 1);
        this.add(email, 1, 2);
        this.add(password, 1, 3);
        this.add(repeatPassword, 1, 4);
        this.add(error, 1, 5);
        this.add(create, 1, 6);

        ColumnConstraints colum1 = new ColumnConstraints(200);
        ColumnConstraints colum2 = new ColumnConstraints(200);
        ColumnConstraints colum3 = new ColumnConstraints(200);
        this.getColumnConstraints().addAll(colum1, colum2, colum3);

        setMargin(back, new Insets(25, 10, 25, 25));
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


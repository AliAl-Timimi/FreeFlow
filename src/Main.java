import be.kdg.freeflow.model.players.Login;
import be.kdg.freeflow.view.login.LoginPresenter;
import be.kdg.freeflow.view.login.LoginView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage){
        final Login login = new Login();
        final LoginView view = new LoginView();
        LoginPresenter presenter = new LoginPresenter(login, view);
        primaryStage.setTitle("FreeFlow By Ali & Gilles");
        Scene scene = new Scene(view);
        presenter.applySettings();
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

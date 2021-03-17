package be.kdg.freeflow.view.highscores;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.Login;
import be.kdg.freeflow.model.Scores;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HighscoresPresenter {
    private HighscoresView view;
    private Login login;
    private Scores scores;
    private MenuView menuView;
    private String scoreString;

    public HighscoresPresenter(HighscoresView highscoresView, Login login, MenuView menuView) {
        this.login = login;
        this.view = highscoresView;
        this.menuView = menuView;
        this.scores= new Scores();
        this.scoreString = scores.highscores(new FreeFlow(login.getPlayer()));
        this.view.getScores().setText(scoreString);
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToMenu();
            }
        });
    }

    private void updateToMenu() {
        view.getScene().setRoot(menuView);
    }
}

package be.kdg.freeflow.view.highscores;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.menus.Scores;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HighscoresPresenter {
    private final HighscoresView view;
    private final Scores scores;
    private final MenuView menuView;

    public HighscoresPresenter(HighscoresView highscoresView, MenuView menuView, FreeFlow game) {
        this.view = highscoresView;
        this.menuView = menuView;
        this.scores = new Scores(game);
        this.view.getScores().setText(scores.highscores());
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateToMenu();
            }
        });
    }

    private void updateToMenu() {
        view.getScene().setRoot(menuView);
    }
}

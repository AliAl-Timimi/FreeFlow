package be.kdg.freeflow.view.levelchooser;

import be.kdg.freeflow.model.Level;
import be.kdg.freeflow.model.Login;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.menu.MenuPresenter;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;;

public class LevelChooserPresenter {

    private Level model;
    private LevelChooserView view;
    private LoginView loginView;
    private Login login;

    public LevelChooserPresenter(Level model, LevelChooserView view) {
        this.view=view;
        this.model=model;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateViewToMenu();
            }
        });

        view.getLevel1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*updateViewToGame();
                 */
            }
        });
    }

    private void updateViewToMenu() {
        MenuView menuView = new MenuView();
        MenuPresenter menuPresenter = new MenuPresenter(menuView, login, loginView);
        view.getScene().setRoot(menuView);
    }
    /*
    private void updateViewToGame() {
        GameView gameView = new GameView();
        GamePresenter gamePresenter = new GamePresenter();
        view.getScene().setRoot(gameView);*/
}

package be.kdg.freeflow.view.popup;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.view.game.GamePresenter;
import be.kdg.freeflow.view.game.GameView;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class PopupPresenter {
    private PopupView view;
    private Level model;
    private MenuView menuView;
    private GameView gameView;
    private LevelChooserView levelChooserView;
    private Setting setting;
    private FreeFlow game;

    public PopupPresenter(Level model, PopupView view, GameView gameView, LevelChooserView levelChooserView, Setting setting, FreeFlow game) {
        this.view=view;
        this.model = model;
        this.gameView=gameView;
        this.levelChooserView=levelChooserView;
        this.setting=setting;
        this.game=game;
        view.getScore().setText(model.starScore());
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                if (model.getLevelnummer()<game.listLevels().size()) {
                    updateToNextGameView();
                }
            }
        });
        view.getReplay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                updateToCurrentGameView();
            }
        });
        view.getMain().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                updateToLevelChooser();
            }
        });
    }

    private void updateToLevelChooser() {
        gameView.getScene().setRoot(levelChooserView);
        model.reset();
        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();
    }

    private void updateToCurrentGameView() {
        model.reset();
        GameView gameView = new GameView(game.chooseLevel(model.getLevelnummer()));
        GamePresenter presenter = new GamePresenter(game.chooseLevel(model.getLevelnummer()), gameView, levelChooserView, setting, game);
        this.gameView.getScene().setRoot(gameView);
        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();
    }

    private void updateToNextGameView() {
        GameView gameView = new GameView(game.chooseLevel(model.getLevelnummer()+1));
        GamePresenter presenter = new GamePresenter(game.chooseLevel(model.getLevelnummer()+1), gameView, levelChooserView, setting, game);
        this.gameView.getScene().setRoot(gameView);
        Stage stage = (Stage) view.getScene().getWindow();
        stage.close();
    }
}
package be.kdg.freeflow.view.levelchooser;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.LevelChooser;
import be.kdg.freeflow.model.menus.Login;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.view.game.GamePresenter;
import be.kdg.freeflow.view.game.GameView;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.menu.MenuPresenter;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;;import java.util.List;

public class LevelChooserPresenter {

    private LevelChooser model;
    private LevelChooserView view;
    private MenuView menuView;
    private LoginView loginView;
    private Login login;
    private List<Level> levels;
    private FreeFlow game;

    public LevelChooserPresenter(LevelChooser model, LevelChooserView view, Login login, MenuView menuView, LoginView loginView, FreeFlow game) {
        this.view = view;
        this.model = model;
        this.login = login;
        this.menuView = menuView;
        this.loginView = loginView;
        this.game = game;
        this.levels = model.levelMenu();
        setLevelText();
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
                int lvl = Integer.parseInt(view.getLevel1().getText().split(" ")[1].replaceAll(":",""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel2().getText().split(" ")[1].replaceAll(":",""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel3().getText().split(" ")[1].replaceAll(":",""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel4().getText().split(" ")[1].replaceAll(":",""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel4().getText().split(" ")[1].replaceAll(":",""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
    }

    private void setLevelText() {
        view.getLevel1().setText(levels.get(0).toString() + (model.isLevelUnlocked(levels.get(0).getLevelNummer())?"":"\uD83D\uDD12"));
        view.getLevel2().setText(levels.get(1).toString() + (model.isLevelUnlocked(levels.get(1).getLevelNummer())?"":"\uD83D\uDD12"));
        view.getLevel3().setText(levels.get(2).toString() + (model.isLevelUnlocked(levels.get(2).getLevelNummer())?"":"\uD83D\uDD12"));
        view.getLevel4().setText(levels.get(3).toString() + (model.isLevelUnlocked(levels.get(3).getLevelNummer())?"":"\uD83D\uDD12"));
        view.getLevel5().setText(levels.get(4).toString() + (model.isLevelUnlocked(levels.get(4).getLevelNummer())?"":"\uD83D\uDD12"));
    }

    private void updateViewToMenu() {
        MenuPresenter menuPresenter = new MenuPresenter(menuView, login, loginView);
        view.getScene().setRoot(menuView);
    }

    private void updateViewToGame(int lvl) {
        GameView gameView = new GameView();
        GamePresenter gamePresenter = new GamePresenter(game.chooseLevel(lvl), gameView);
        view.getScene().setRoot(gameView);
    }
}

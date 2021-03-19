package be.kdg.freeflow.view.levelchooser;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.LevelChooser;
import be.kdg.freeflow.model.players.Login;
import be.kdg.freeflow.model.players.SaveToFile;
import be.kdg.freeflow.view.game.GamePresenter;
import be.kdg.freeflow.view.game.GameView;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.menu.MenuPresenter;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;


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
                int lvl = Integer.parseInt(view.getLevel1().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel2().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel3().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel4().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int lvl = Integer.parseInt(view.getLevel4().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getPrev().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (model.prevPage()) {
                    levels = model.levelMenu();
                    setLevelText();
                }
            }
        });
        view.getNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (model.nextPage()) {
                    levels = model.levelMenu();
                    setLevelText();
                }
            }
        });
    }

    private void setLevelText() {
        if (levels.size() >= 1 && levels.get(0) != null)
            view.getLevel1().setText(levels.get(0).toString() + (model.isLevelUnlocked(levels.get(0).getLevelNummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel1().setText("");
        if (levels.size() >1 && levels.get(1) != null)
            view.getLevel2().setText(levels.get(1).toString() + (model.isLevelUnlocked(levels.get(1).getLevelNummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel2().setText("");
        if (levels.size() >2 && levels.get(2) != null)
            view.getLevel3().setText(levels.get(2).toString() + (model.isLevelUnlocked(levels.get(2).getLevelNummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel3().setText("");
        if (levels.size() >3 && levels.get(3) != null)
            view.getLevel4().setText(levels.get(3).toString() + (model.isLevelUnlocked(levels.get(3).getLevelNummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel4().setText("");
        if (levels.size() >4 && levels.get(4) != null)
            view.getLevel5().setText(levels.get(4).toString() + (model.isLevelUnlocked(levels.get(4).getLevelNummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel5().setText("");

    }

    private void updateViewToMenu() {
        MenuPresenter menuPresenter = new MenuPresenter(menuView, login, loginView);
        view.getScene().setRoot(menuView);
    }

    private void updateViewToGame(int lvl) {
        GameView gameView = new GameView(game.chooseLevel(lvl));
        GamePresenter gamePresenter = new GamePresenter(game.chooseLevel(lvl), gameView, view);
        view.getScene().setRoot(gameView);
    }
}

package be.kdg.freeflow.view.levelchooser;

import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.model.menus.LevelChooser;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.model.players.Login;
import be.kdg.freeflow.view.game.GamePresenter;
import be.kdg.freeflow.view.game.GameView;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class LevelChooserPresenter {

    private final LevelChooser model;
    private final LevelChooserView view;
    private final MenuView menuView;
    private final LoginView loginView;
    private final Login login;
    private List<Level> levels;
    private final FreeFlow game;
    private final Setting setting;

    public LevelChooserPresenter(LevelChooser model, LevelChooserView view, Login login, MenuView menuView, LoginView loginView, Setting setting, FreeFlow game) {
        this.view = view;
        this.model = model;
        this.login = login;
        this.menuView = menuView;
        this.loginView = loginView;
        this.game = game;
        this.levels = model.levelMenu();
        this.setting = setting;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLevelText();
            }
        });
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                updateViewToMenu();
            }
        });

        view.getLevel1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                int lvl = Integer.parseInt(view.getLevel1().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                int lvl = Integer.parseInt(view.getLevel2().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                int lvl = Integer.parseInt(view.getLevel3().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                int lvl = Integer.parseInt(view.getLevel4().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getLevel5().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.play();
                int lvl = Integer.parseInt(view.getLevel5().getText().split(" ")[1].replaceAll(":", ""));
                if (model.isLevelUnlocked(lvl))
                    updateViewToGame(lvl);
            }
        });
        view.getPrev().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                if (model.prevPage()) {
                    levels = model.levelMenu();
                    setLevelText();
                }
            }
        });
        view.getNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                if (model.nextPage()) {
                    levels = model.levelMenu();
                    setLevelText();
                }
            }
        });
    }

    public void setLevelText() {
        if (levels.size() >= 1 && levels.get(0) != null)
            view.getLevel1().setText(levels.get(0).toString() + (model.isLevelUnlocked(levels.get(0).getLevelnummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel1().setText("");
        if (levels.size() > 1 && levels.get(1) != null)
            view.getLevel2().setText(levels.get(1).toString() + (model.isLevelUnlocked(levels.get(1).getLevelnummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel2().setText("");
        if (levels.size() > 2 && levels.get(2) != null)
            view.getLevel3().setText(levels.get(2).toString() + (model.isLevelUnlocked(levels.get(2).getLevelnummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel3().setText("");
        if (levels.size() > 3 && levels.get(3) != null)
            view.getLevel4().setText(levels.get(3).toString() + (model.isLevelUnlocked(levels.get(3).getLevelnummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel4().setText("");
        if (levels.size() > 4 && levels.get(4) != null)
            view.getLevel5().setText(levels.get(4).toString() + (model.isLevelUnlocked(levels.get(4).getLevelnummer()) ? "" : "\uD83D\uDD12"));
        else
            view.getLevel5().setText("");
    }

    private void updateViewToMenu() {
        view.getScene().setRoot(menuView);
    }

    private void updateViewToGame(int lvl) {
        GameView gameView = new GameView(game.chooseLevel(lvl));
        GamePresenter gamePresenter = new GamePresenter(game.chooseLevel(lvl), gameView, view, setting, game);
        view.getScene().setRoot(gameView);
    }
}

package be.kdg.freeflow.view.menu;


import be.kdg.freeflow.model.FreeFlow;
import be.kdg.freeflow.model.FreeFlowException;
import be.kdg.freeflow.model.menus.LevelChooser;
import be.kdg.freeflow.model.menus.Setting;
import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.model.players.Login;
import be.kdg.freeflow.model.players.ReadFromFile;
import be.kdg.freeflow.model.players.SaveToFile;
import be.kdg.freeflow.view.help.HelpPresenter;
import be.kdg.freeflow.view.help.HelpView;
import be.kdg.freeflow.view.highscores.HighscoresPresenter;
import be.kdg.freeflow.view.highscores.HighscoresView;
import be.kdg.freeflow.view.levelchooser.LevelChooserPresenter;
import be.kdg.freeflow.view.levelchooser.LevelChooserView;
import be.kdg.freeflow.view.login.LoginView;
import be.kdg.freeflow.view.settings.SettingsPresenter;
import be.kdg.freeflow.view.settings.SettingsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuPresenter {
    private final MenuView view;
    private final Login login;
    private final LoginView loginView;
    private final Setting setting;
    private final FreeFlow game;


    public MenuPresenter(MenuView menuView, Login login, LoginView loginView, Setting setting) {
        this.view = menuView;
        this.login = login;
        this.loginView = loginView;
        ReadFromFile.setPlayer(login.getPlayer());
        ReadFromFile.setGame();
        FreeFlow game1 = ReadFromFile.read();
        if (game1 != null)
            this.game = game1;
        else
            throw new FreeFlowException("Game is null");
        this.setting = setting;
        SaveToFile.setPlayer(login.getPlayer());
        addEventHandlers();
    }


    private void addEventHandlers() {
        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateToLevels();
            }
        });
        view.getHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateToHelp();
            }
        });
        view.getScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateToScores();
            }
        });
        view.getSettings().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateToSettings();
            }
        });
        view.getLogout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                login.logout();
                updateToLogin();
            }
        });
    }

    private void updateToHelp() {
        HelpView helpView = new HelpView();
        HelpPresenter helpPresenter = new HelpPresenter(helpView, view);
        view.getScene().setRoot(helpView);
    }

    private void updateToSettings() {
        SettingsView settingsView = new SettingsView();
        SettingsPresenter settingsPresenter = new SettingsPresenter(setting, settingsView, view);
        view.getScene().setRoot(settingsView);
    }

    private void updateToScores() {
        HighscoresView highscoresView = new HighscoresView();
        HighscoresPresenter highscoresPresenter = new HighscoresPresenter(highscoresView, view, game);
        view.getScene().setRoot(highscoresView);
    }

    private void updateToLevels() {
        LevelChooserView levelChooserView = new LevelChooserView();
        LevelChooser levelChooser = new LevelChooser(game);
        LevelChooserPresenter levelChooserPresenter = new LevelChooserPresenter(levelChooser, levelChooserView, login, view, loginView, setting, game);
        view.getScene().setRoot(levelChooserView);
    }

    private void updateToLogin() {
        view.getScene().setRoot(loginView);
    }
}

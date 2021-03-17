package be.kdg.freeflow.view.menu;


import be.kdg.freeflow.model.Login;
import be.kdg.freeflow.model.Setting;
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
    private MenuView view;
    private Login login;
    private LoginView loginView;

    public MenuPresenter(MenuView menuView, Login login, LoginView loginView) {
        this.view = menuView;
        this.login = login;
        this.loginView = loginView;
        addEventHandlers();
    }


    private void addEventHandlers() {
        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToLevels();
            }
        });
        view.getHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToHelp();
            }
        });
        view.getScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToScores();
            }
        });
        view.getSettings().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateToSettings();
            }
        });
        view.getLogout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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
        Setting setting = new Setting();
        SettingsView settingsView = new SettingsView();
        SettingsPresenter settingsPresenter = new SettingsPresenter(setting, settingsView, view);
        view.getScene().setRoot(settingsView);
    }

    private void updateToScores() {
        HighscoresView highscoresView = new HighscoresView();
        HighscoresPresenter highscoresPresenter = new HighscoresPresenter(highscoresView);
        view.getScene().setRoot(highscoresView);
    }

    private void updateToLevels() {
        LevelChooserView levelChooserView = new LevelChooserView();
        LevelChooserPresenter levelChooserPresenter = new LevelChooserPresenter(levelChooserView);
        view.getScene().setRoot(levelChooserView);
    }

    private void updateToLogin() {
        view.getScene().setRoot(loginView);
    }
}

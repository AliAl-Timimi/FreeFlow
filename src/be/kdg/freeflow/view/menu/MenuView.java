package be.kdg.freeflow.view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MenuView extends BorderPane {
    private GridPane gridPane;
    private Label title;
    private Button logout;
    private Button start;
    private Button scores;
    private Button help;
    private Button settings;

    public MenuView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        gridPane = new GridPane();
        title = new Label("Main Menu");
        logout = new Button("logout");
        start = new Button("Start Game");
        scores = new Button("Highscores");
        help = new Button("Help");
        settings = new Button("Instellingen");
    }

    private void layoutNodes() {
        this.setLeft(logout);
        this.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(title,1,1,2,1);
        gridPane.add(start,1,2);
        gridPane.add(settings, 2,2);
        gridPane.add(scores, 1,3);
        gridPane.add(help,2,3);

        start.setPrefWidth(100);
        settings.setPrefWidth(100);
        help.setPrefWidth(100);
        scores.setPrefWidth(100);

        setMargin(logout, new Insets(10));
        GridPane.setMargin(title, new Insets(10,0,10,0));
        GridPane.setMargin(start, new Insets(10,10,10,0));
        GridPane.setMargin(settings, new Insets(10,0,10,0));
        GridPane.setMargin(scores, new Insets(10,10,10,0));
        GridPane.setMargin(help, new Insets(10,0,10,0));

        title.setId("titles");
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button getLogout() {
        return logout;
    }

    public Button getStart() {
        return start;
    }

    public Button getScores() {
        return scores;
    }

    public Button getHelp() {
        return help;
    }

    public Button getSettings() {
        return settings;
    }
}

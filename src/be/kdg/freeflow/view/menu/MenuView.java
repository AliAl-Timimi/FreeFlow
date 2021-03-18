package be.kdg.freeflow.view.menu;

import be.kdg.freeflow.model.Grid;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class MenuView extends GridPane {
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
        title = new Label("Main Menu");
        logout = new Button("logout");
        start = new Button("Start Game");
        scores = new Button("Highscores");
        help = new Button("Help");
        settings = new Button("Instellingen");
    }

    private void layoutNodes() {
        GridPane.setHalignment(title, HPos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.add(logout, 0, 0);
        this.add(title,1,1,2,1);
        this.add(start,1,2);
        this.add(settings, 2,2);
        this.add(scores, 1,3);
        this.add(help,2,3);

        start.setPrefWidth(100);
        settings.setPrefWidth(100);
        help.setPrefWidth(100);
        scores.setPrefWidth(100);

        ColumnConstraints colum1 = new ColumnConstraints(100);
        ColumnConstraints colum2 = new ColumnConstraints(100);
        ColumnConstraints colum3 = new ColumnConstraints(100);
        ColumnConstraints colum4 = new ColumnConstraints(100);
        this.getColumnConstraints().addAll(colum1, colum2, colum3, colum4);

        setMargin(logout, new Insets(10));
        GridPane.setMargin(title, new Insets(10,0,10,0));
        GridPane.setMargin(start, new Insets(10,5,10,0));
        GridPane.setMargin(settings, new Insets(10,0,10,5));
        GridPane.setMargin(scores, new Insets(10,5,10,0));
        GridPane.setMargin(help, new Insets(10,0,10,5));

        title.setId("titles");
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

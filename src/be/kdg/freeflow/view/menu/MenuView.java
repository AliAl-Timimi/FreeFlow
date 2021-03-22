package be.kdg.freeflow.view.menu;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class MenuView extends GridPane {
    private TextFlow flow;
    private Text titelp1;
    private Text titelp2;
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
        titelp1 = new Text("Main");
        titelp2 = new Text(" Menu");
        flow = new TextFlow();
        flow.getChildren().addAll(titelp1, titelp2);
        logout = new Button("log uit");
        start = new Button("Start Game");
        scores = new Button("Highscores");
        help = new Button("Help");
        settings = new Button("Instellingen");
    }

    private void layoutNodes() {

        titelp1.setId("titelp1");
        titelp2.setId("titelp2");
        this.add(flow, 1, 1, 2, 1);
        flow.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(flow, HPos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.add(logout, 0, 0);
        this.add(start, 1, 2);
        this.add(settings, 2, 2);
        this.add(scores, 1, 3);
        this.add(help, 2, 3);

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
        GridPane.setMargin(start, new Insets(10, 5, 10, 0));
        GridPane.setMargin(settings, new Insets(10, 0, 10, 5));
        GridPane.setMargin(scores, new Insets(10, 5, 10, 0));
        GridPane.setMargin(help, new Insets(10, 0, 10, 5));

        flow.setId("titles");
        logout.setId("back");
        start.setId("start");
        settings.setId("settings");
        scores.setId("scores");
        help.setId("help");
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

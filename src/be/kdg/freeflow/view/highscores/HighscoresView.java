package be.kdg.freeflow.view.highscores;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class HighscoresView extends GridPane {
    private Button back;
    private Label title;
    private Label scores;

    public HighscoresView() {
        initialisdeNodes();
        layoutNodes();
    }

    private void initialisdeNodes() {
        back = new Button("Terug");
        title = new Label("Highscores");
        scores = new Label();
    }

    private void layoutNodes() {

        this.setAlignment(Pos.CENTER);
        GridPane.setHalignment(scores, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        this.add(back, 0, 0);
        this.add(title, 1, 1);
        this.add(scores, 1, 2);

        ColumnConstraints colum1 = new ColumnConstraints(100);
        ColumnConstraints colum2 = new ColumnConstraints(300);
        ColumnConstraints colum3 = new ColumnConstraints(100);
        this.getColumnConstraints().addAll(colum1, colum2, colum3);

        setMargin(back, new Insets(10));
        GridPane.setMargin(title, new Insets(0, 0, 10, 0));

        title.setId("titles");
        scores.setId("highscores");
    }

    public Button getBack() {
        return back;
    }

    public Label getScores() {
        return scores;
    }
}

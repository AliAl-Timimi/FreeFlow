package be.kdg.freeflow.view.highscores;

import be.kdg.freeflow.model.Grid;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class HighscoresView extends BorderPane {
    private Button back;
    private GridPane gridPane;
    private Label title;
    private Label scores;

    public HighscoresView() {
        initialisdeNodes();
        layoutNodes();
    }

    private void initialisdeNodes() {
        back = new Button("Terug");
        gridPane = new GridPane();
        title = new Label("Highscores");
        scores = new Label();
    }

    private void layoutNodes() {
        setLeft(back);
        setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(title,1,1,2,1);
        gridPane.add(scores,1,2,2,4);

        setMargin(back, new Insets(5));
        GridPane.setMargin(title, new Insets(0,0,10,0));
    }

    public Button getBack() {
        return back;
    }

    public Label getScores() {
        return scores;
    }
}

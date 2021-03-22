package be.kdg.freeflow.view.popup;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopupView extends VBox {
    private Label score;
    private Button next;
    private Button replay;
    private Button main;

    public PopupView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        score = new Label("null");
        next = new Button("Volgend level");
        replay = new Button("Speel opnieuw");
        main = new Button("Level menu");
    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(score, next, replay, main);

        score.setId("score");
    }

    public Label getScore() {
        return score;
    }

    public Button getNext() {
        return next;
    }

    public Button getReplay() {
        return replay;
    }

    public Button getMain() {
        return main;
    }
}
package be.kdg.freeflow.view.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SettingsView extends BorderPane {
    private Button back;
    private Label title;
    private GridPane gridPane;
    private Button styleButton;
    private Button sound;
    private Button passwordChange;

    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        back = new Button("Terug");
        gridPane = new GridPane();
        title = new Label("Instellingen");
        styleButton = new Button("Stijl: Classic");
        sound = new Button("SFX: Aan");
    }

    private void layoutNodes() {
        this.setLeft(back);
        this.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(title,1,1,2,1);
        gridPane.add(styleButton,1,2,2,1);
        gridPane.add(sound,1,3,2,1);

        setMargin(back, new Insets(5));
        GridPane.setMargin(title, new Insets(10,0,10,0));
        GridPane.setMargin(styleButton, new Insets(10,0,10,0));
        GridPane.setMargin(sound, new Insets(10,0,10,0));
    }

    public Button getBack() {
        return back;
    }


    public Button getStyleButton() {
        return styleButton;
    }

    public Button getSound() {
        return sound;
    }

}

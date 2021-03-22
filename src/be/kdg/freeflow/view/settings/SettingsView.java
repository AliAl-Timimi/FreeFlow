package be.kdg.freeflow.view.settings;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class SettingsView extends GridPane {
    private Button back;
    private Label title;
    private Button styleButton;
    private Button sound;

    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        back = new Button("Terug");
        title = new Label("Instellingen");
        styleButton = new Button();
        sound = new Button();
    }

    private void layoutNodes() {

        this.setAlignment(Pos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(styleButton, HPos.CENTER);
        GridPane.setHalignment(sound, HPos.CENTER);

        this.add(back, 0, 0);
        this.add(title, 1, 1, 2, 1);
        this.add(styleButton, 1, 2, 2, 1);
        this.add(sound, 1, 3, 2, 1);

        ColumnConstraints colum1 = new ColumnConstraints(100);
        ColumnConstraints colum2 = new ColumnConstraints(100);
        ColumnConstraints colum3 = new ColumnConstraints(100);
        ColumnConstraints colum4 = new ColumnConstraints(100);
        this.getColumnConstraints().addAll(colum1, colum2, colum3, colum4);

        setMargin(back, new Insets(5));
        GridPane.setMargin(title, new Insets(10, 0, 10, 0));
        GridPane.setMargin(styleButton, new Insets(10, 0, 10, 0));
        GridPane.setMargin(sound, new Insets(10, 0, 10, 0));

        title.setId("titles");
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

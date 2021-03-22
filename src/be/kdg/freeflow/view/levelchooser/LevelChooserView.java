package be.kdg.freeflow.view.levelchooser;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;


public class LevelChooserView extends GridPane {
    private Label title;
    private Button back;
    private Button prev;
    private Button next;
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button level5;

    public LevelChooserView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        title = new Label("Levels");
        back = new Button("Terug");
        prev = new Button("prev");
        next = new Button("next");
        level1 = new Button();
        level2 = new Button();
        level3 = new Button();
        level4 = new Button();
        level5 = new Button();
    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(next, HPos.RIGHT);
        this.add(back, 0, 0);
        this.add(title, 1, 1, 2, 1);
        this.add(prev, 1, 7);
        this.add(next, 2, 7);
        this.add(level1, 1, 2, 2, 1);
        this.add(level2, 1, 3, 2, 1);
        this.add(level3, 1, 4, 2, 1);
        this.add(level4, 1, 5, 2, 1);
        this.add(level5, 1, 6, 2, 1);

        ColumnConstraints colum1 = new ColumnConstraints(100);
        ColumnConstraints colum2 = new ColumnConstraints(100);
        ColumnConstraints colum3 = new ColumnConstraints(100);
        ColumnConstraints colum4 = new ColumnConstraints(100);
        this.getColumnConstraints().addAll(colum1, colum2, colum3, colum4);


        level1.setPrefWidth(200);
        level2.setPrefWidth(200);
        level3.setPrefWidth(200);
        level4.setPrefWidth(200);
        level5.setPrefWidth(200);

        setMargin(back, new Insets(10));
        GridPane.setMargin(title, new Insets(10, 0, 10, 0));
        GridPane.setMargin(level1, new Insets(10, 0, 10, 0));
        GridPane.setMargin(level2, new Insets(0, 0, 10, 0));
        GridPane.setMargin(level3, new Insets(0, 0, 10, 0));
        GridPane.setMargin(level4, new Insets(0, 0, 10, 0));
        GridPane.setMargin(level5, new Insets(0, 0, 10, 0));
        GridPane.setMargin(prev, new Insets(0, 0, 10, 0));
        GridPane.setMargin(next, new Insets(0, 0, 10, 0));

        title.setId("titles");
        level1.setId("level1");
        level2.setId("level2");
        level3.setId("level3");
        level4.setId("level4");
        level5.setId("level5");
    }

    Button getBack() {
        return back;
    }

    Button getPrev() {
        return prev;
    }

    Button getNext() {
        return next;
    }

    Button getLevel1() {
        return level1;
    }

    Button getLevel2() {
        return level2;
    }

    Button getLevel3() {
        return level3;
    }

    Button getLevel4() {
        return level4;
    }

    Button getLevel5() {
        return level5;
    }
}

package be.kdg.freeflow.view.help;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class HelpView extends GridPane {
    private Button back;
    private Label title;
    private Label help;

    public HelpView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        back = new Button("Terug");
        title = new Label("Help");
        help = new Label();

        help.setText(
                "Het doel van het spel is om alle bollen\n" +
                        "die dezelfde kleur hebben met elkaar te verbinden\n" +
                        "zonder dat deze verbindingen over een andere verbinding gaan\n" +
                        "Wanneer elke bol met de andere bol van dezelfde kleur verbonden is,\n" +
                        "is dat level gedaan. Probeer elk level met zo weinig mogelijk moves\n" +
                        "af te maken om de hoogst mogelijke score te behalen!");
    }

    private void layoutNodes() {
        GridPane.setHalignment(title, HPos.LEFT);
        GridPane.setHalignment(help, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.add(back, 0, 0);
        this.add(title, 1, 1);
        this.add(help, 1, 2);

        ColumnConstraints colum1 = new ColumnConstraints(100);
        ColumnConstraints colum2 = new ColumnConstraints(400);
        ColumnConstraints colum3 = new ColumnConstraints(100);

        this.getColumnConstraints().addAll(colum1, colum2, colum3);

        GridPane.setMargin(title, new Insets(0, 0, 10, 5));
        setMargin(back, new Insets(25, 10, 25, 25));

        title.setId("titles");
        help.setId("helptekst");
    }

    public Button getBack() {
        return back;
    }
}

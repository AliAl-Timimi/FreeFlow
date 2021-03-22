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
    private Label controls;

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
        controls = new Label();

        controls.setText(
                "klik op een bol en sleep met je muis over het veld terwijl je\n" +
                        "de muis ingedrukt houdt\n" +
                        "om een lijn te trekken naar een andere bol van dezelfde kleur,\n" +
                        "laat de muis los om te stoppen\n" +
                        "Je kan terug klikken op een lijn om verder te gaan waar je gebleven bent.\n" +
                        "Rechtsklik op een bol om een getrokken lijn van dezelfde kleur weg te doen.");
    }

    private void layoutNodes() {
        GridPane.setHalignment(title, HPos.LEFT);
        GridPane.setHalignment(help, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(controls, HPos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.add(back, 0, 0);
        this.add(title, 1, 1);
        this.add(help, 1, 2);
        this.add(controls, 1, 3);

        ColumnConstraints colum1 = new ColumnConstraints(100);
        ColumnConstraints colum2 = new ColumnConstraints(400);
        ColumnConstraints colum3 = new ColumnConstraints(100);

        this.getColumnConstraints().addAll(colum1, colum2, colum3);

        GridPane.setMargin(title, new Insets(0, 0, 10, 5));
        setMargin(back, new Insets(25, 10, 25, 25));
        setMargin(help, new Insets(0, 0, 0, 15));
        setMargin(controls, new Insets(15, 0, 0, 0));

        title.setId("titles");
        help.setId("helptekst");
        controls.setId("controls");
    }

    public Button getBack() {
        return back;
    }
}

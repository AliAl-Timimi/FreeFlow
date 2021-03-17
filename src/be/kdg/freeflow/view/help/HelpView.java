package be.kdg.freeflow.view.help;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HelpView extends BorderPane {
    private GridPane gridPane;
    private Button back;
    private Label title;
    private Label help;

    public HelpView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        gridPane = new GridPane();
        back = new Button("Terug");
        title = new Label("Help");
        help = new Label();

        help.setText(
                "Het doel van het spel is om alle bollen (letters)\n" +
                "die dezelfde kleur hebben met elkaar te verbinden\n" +
                "zonder dat deze verbindingen over een andere verbinding gaan\n" +
                "Wanneer elke bol met de andere bol van dezelfde kleur verbonden is\n" +
                "is dat level gedaan. Probeer elk level met zo weinig mogelijk moves\n" +
                "af te maken om de hoogst mogelijke score te behalen!");
    }

    private void layoutNodes() {
        this.setLeft(back);
        this.setCenter(gridPane);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(title,1,1);
        gridPane.add(help,1,2);

        GridPane.setMargin(title, new Insets(0,0,10,0));
        setMargin(back, new Insets(25));

        title.setId("titles");
    }

    public Button getBack() {
        return back;
    }
}

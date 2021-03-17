package be.kdg.freeflow.view.help;

import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HelpPresenter {
    private HelpView view;
    private MenuView menuView;

    public HelpPresenter(HelpView helpView, MenuView menuView) {
        this.view = helpView;
        this.menuView = menuView;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateView();
            }
        });
    }

    private void updateView() {
        view.getScene().setRoot(menuView);
    }
}

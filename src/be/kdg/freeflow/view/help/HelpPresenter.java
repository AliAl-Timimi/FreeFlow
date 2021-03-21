package be.kdg.freeflow.view.help;

import be.kdg.freeflow.model.menus.Sound;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HelpPresenter {
    private final HelpView VIEW;
    private final MenuView menuView;

    public HelpPresenter(HelpView helpView, MenuView menuView) {
        this.VIEW = helpView;
        this.menuView = menuView;
        addEventHandlers();
    }

    private void addEventHandlers() {
        VIEW.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.play();
                updateView();
            }
        });
    }

    private void updateView() {
        VIEW.getScene().setRoot(menuView);
    }
}

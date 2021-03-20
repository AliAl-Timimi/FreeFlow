package be.kdg.freeflow.view.popup;

import be.kdg.freeflow.model.lvlbuild.Level;
import be.kdg.freeflow.view.menu.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PopupPresenter {
    private PopupView view;
    private Level model;
    private MenuView menuView;

    public PopupPresenter(Level model, PopupView view) {
        this.view=view;
        this.model = model;
        view.getScore().setText(model.starScore());
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*updateToGameView();*/
            }
        });
        view.getReplay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*updateToCurrentGameView();*/
            }
        });
        view.getMain().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*updateToMenuView();*/
            }
        });
    }

    /*private void updateToMenuView() {
        view.getScene().setRoot(menuView);
    }*/
}

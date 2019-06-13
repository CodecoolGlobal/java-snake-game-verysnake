package com.codecool.snake.entities.technical;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class Text extends GameEntity {

    private Label label = new Label();

    public Popup getPopup() {
        return popup;
    }

    private Popup popup = new Popup();

    public void setLabel(String text) {
        this.label.setText(text);
        popup.getContent().add(label);
        label.setStyle(" -fx-background-color: yellow;");
        label.setFont(new Font("Cambria", 32));
    }

    public void show(Stage stage) {
        popup.show(stage);
    }

    public void hide() {
        popup.hide();
    }

}

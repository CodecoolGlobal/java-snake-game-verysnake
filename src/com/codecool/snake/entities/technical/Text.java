package com.codecool.snake.entities.technical;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;




public class Text extends GameEntity {
    private Label label = new Label();
    private Popup popup = new Popup();


    public void setLabel(String text) {
        this.label.setText(text);
        popup.getContent().add(label);
    }


    public void show(Stage stage){
        popup.show(stage);
    }
}

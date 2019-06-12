package com.codecool.snake;

import com.codecool.snake.entities.snakes.Snake;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static javafx.scene.layout.HBox.setMargin;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #336699;");

        Label healthLabel = new Label("Health: ");
        Label healthValue = new Label(String.valueOf(Globals.getInstance().game.getSnake().getHealth()));
        Globals.getInstance().healthValue = healthValue;
        hbox.getChildren().addAll(healthLabel, healthValue);

        setMargin(healthLabel, new Insets(5, 10, 5, 50));
        setMargin(healthValue, new Insets(5, 10, 5, 10));

        pane.setTop(hbox);
        pane.setCenter(game);

        Scene mainScene = new Scene(pane, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}

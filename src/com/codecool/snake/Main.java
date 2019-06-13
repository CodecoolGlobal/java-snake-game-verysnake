package com.codecool.snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        Globals.getInstance().stage = primaryStage;

        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #336699;");

        Button restartButton = new Button();
        restartButton.setText("Restart");
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("restart button clicked");
                game.restart();
            }
        });

        Label healthLabel = new Label("Health: ");
        Label healthValue = new Label(String.valueOf(Globals.getInstance().game.getSnake().getHealth()));
        Globals.getInstance().healthValue = healthValue;
        hbox.getChildren().addAll(healthLabel, healthValue, restartButton);

        setMargin(healthLabel, new Insets(5, 10, 5, 50));
        setMargin(healthValue, new Insets(5, 10, 5, 10));
        setMargin(restartButton, new Insets(5, 10, 5, 300));

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

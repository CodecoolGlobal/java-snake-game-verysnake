package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class CodecoolPowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();
    private final int power;

    private Timeline timeline;

    public int getPower() {
        return power;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public CodecoolPowerUp() {
        setImage(Globals.getInstance().getImage("CodecoolLogo"));
        power = 5;
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(0);

    }

    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(3),
                new KeyValue(this.layoutYProperty(), Globals.WINDOW_HEIGHT)));
        timeline.setCycleCount(1);
        timeline.play();
        Globals.getInstance().codecoolPowerUp = this;
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got Codecool power-up";
    }
}

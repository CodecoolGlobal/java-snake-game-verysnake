package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class GreenFox extends Enemy implements Animatable, Interactable {

    private static Random rnd = new Random();

    public GreenFox() {
        super(100);
        setImage(Globals.getInstance().getImage("GreenFoxEnemy"));
        setX(0);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT-100);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new KeyValue(this.layoutXProperty(), Globals.WINDOW_WIDTH + 500)));
        timeline.setCycleCount(1);
        timeline.play();
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
        return (getDamage() + " damaged by GreenFox");
    }
}

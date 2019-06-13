package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

public class DarthEnemy extends Enemy implements Animatable, Interactable {

    private static Random rnd = new Random();

    public DarthEnemy() {
        super(20);

        boolean positioned = false;

        setImage(Globals.getInstance().getImage("DarthEnemy"));

        double tryX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double tryY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;

        while (!positioned) {
            if (480 < tryX && tryX < 520 && 480 < tryY && tryY < 520) {
                tryX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                tryY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            } else {
                positioned = true;
            }
        }

        setX(tryX);
        setY(tryY);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(getX() - 60 + 1); // to simulate a full 360 degree celcius circle.
        arcTo.setY(getY() - 60);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(30);
        arcTo.setRadiusY(30);

        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(getX() - 60, getY() - 60),
                arcTo,
                new ClosePath());

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();
    }

    @Override
    public void step() {
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damaged by Darth Vader");
    }
}


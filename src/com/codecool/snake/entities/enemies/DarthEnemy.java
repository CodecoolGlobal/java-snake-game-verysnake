package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

public class DarthEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public DarthEnemy() {
        super(20);

        setImage(Globals.getInstance().getImage("DarthEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        Path path = new Path();
        path.getElements().add(new MoveTo(getX(),getY()));
        ArcTo arcTo1 = new ArcTo();
        arcTo1.setRadiusX(30.0);
        arcTo1.setRadiusY(30.0);
        arcTo1.setX(getX());
        arcTo1.setY(getY());
        ArcTo arcTo2 = new ArcTo();
        arcTo2.setRadiusX(30.0);
        arcTo2.setRadiusY(30.0);
        arcTo2.setX(getX());
        arcTo2.setY(getY());

        path.getElements().addAll(arcTo1, arcTo2);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
//        pathTransition.setAutoReverse(true);
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


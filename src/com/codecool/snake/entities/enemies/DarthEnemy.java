package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class DarthEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
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

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 2;
        heading = Utils.directionToVector(direction, speed);

    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
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


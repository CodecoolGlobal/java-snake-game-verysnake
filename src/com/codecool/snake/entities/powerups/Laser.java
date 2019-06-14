package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.GreenFox;
import javafx.geometry.Point2D;

import java.util.Random;

public class Laser extends GameEntity implements Animatable, Interactable {
    private final int power;
    private Point2D heading;
    private static Random rnd = new Random();

    public Laser(GameEntity entity) {
        power = 10;
        setImage(Globals.getInstance().getImage("Laser"));
        setPosition(entity.getPosition());

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 3;
        heading = Utils.directionToVector(direction, speed);
    }

    public int getPower() {
        return power;
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
        if (entity instanceof Enemy) {
            System.out.println(getMessage());
            entity.destroy();
            Globals.getInstance().game.getSnake().changeHealth(power);
        }
        if (entity instanceof GreenFox) {
            System.out.println(getMessage());
            entity.destroy();
            Globals.getInstance().game.getSnake().changeHealth(power*5);
        }
    }

    @Override
    public String getMessage() {
        return (getPower() + " power");
    }
}

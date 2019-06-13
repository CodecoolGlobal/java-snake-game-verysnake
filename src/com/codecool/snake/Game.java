package com.codecool.snake;

import com.codecool.snake.entities.enemies.DarthEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.CodecoolPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedDownPowerUp;
import com.codecool.snake.entities.powerups.SpeedUpPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private Timeline timeline = new Timeline();
    private boolean restarted = false;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps();

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    public void restart() {
        restarted = true;
        Globals.getInstance().stopGame();
        Globals.getInstance().game.getTimeline().stop();
        Globals.getInstance().codecoolPowerUp.getTimeline().stop();
        Globals.getInstance().text.hide();
        Globals.getInstance().display.clear();
        System.out.println(Globals.getInstance().display.getObjectList().isEmpty());
        init();
        Globals.getInstance().game.getSnake().setHealth(100);
        Globals.getInstance().healthValue.setText(String.valueOf( Globals.getInstance().game.getSnake().getHealth()));
        Globals.getInstance().game.getSnake().setSpeed(2);
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    public Snake getSnake() {
        return snake;
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) {
            new SimpleEnemy();
            new DarthEnemy();
        }
    }

    public Timeline getTimeline() {
        return timeline;
    }

    private void spawnPowerUps() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyFrame simplePowerUp = new KeyFrame(
                Duration.seconds(2),
                ae -> {new SimplePowerUp();
                });
        KeyFrame speedUpPowerUp = new KeyFrame(
                Duration.seconds(5),
                ae -> {new SpeedUpPowerUp();
                });
        KeyFrame speedDownPowerUp = new KeyFrame(
                Duration.seconds(8),
                ae -> {new SpeedDownPowerUp();
                });
        KeyFrame codecoolPowerUp = new KeyFrame(
                Duration.seconds(2),
                ae -> {new CodecoolPowerUp().step();
                });
        if (!restarted) {
            timeline.getKeyFrames().addAll(simplePowerUp, speedUpPowerUp, speedDownPowerUp, codecoolPowerUp);
        }
        timeline.play();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}

package com.codecool.snake;

import com.codecool.snake.entities.enemies.DarthEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedDownPowerUp;
import com.codecool.snake.entities.powerups.SpeedUpPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(5);

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
        Globals.getInstance().stopGame();
        Globals.getInstance().display.clear();
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

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) {new SimplePowerUp(); new SpeedUpPowerUp(); new SpeedDownPowerUp();}
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}

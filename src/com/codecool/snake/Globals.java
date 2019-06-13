package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.technical.Text;
import javafx.scene.image.Image;
import javafx.stage.Stage;


// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Stage stage;

    public Display display;
    public Game game;

    public Text text;

    public Snake snake;

    private GameLoop gameLoop;
    private Resources resources;

    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() {
        gameLoop.stop();
        display.clear();
        Text gameOver = new Text();
        gameOver.setLabel( "No way, you loose the game! You had "+snake.getHealth()+ " health points.");
        Globals.getInstance().text = gameOver;
        gameOver.show(stage);
    }
    private Globals() {
        // singleton needs the class to have private constructor
    }
}

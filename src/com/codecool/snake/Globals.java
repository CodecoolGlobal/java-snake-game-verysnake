package com.codecool.snake;

import com.codecool.snake.entities.enemies.DarthEnemy;
import com.codecool.snake.entities.technical.Text;
import com.codecool.snake.entities.powerups.CodecoolPowerUp;
import com.codecool.snake.resources.Resources;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;



// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;
    public Label healthValue;
    public Stage stage;
    public CodecoolPowerUp codecoolPowerUp;
    public DarthEnemy darthEnemy;
    private GameLoop gameLoop;
    private Resources resources;
    public Text text;


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
        resources.addImage("PowerUpSun", new Image("powerup_sun.png"));
        resources.addImage("PowerUpCloud", new Image("powerup_cloud.png"));
        resources.addImage("DarthEnemy", new Image("darthEnemy.png"));
        resources.addImage("CodecoolLogo", new Image("codecoolLogo.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() {
        gameLoop.stop();
        display.clear();
        Text gameOver = new Text();
        gameOver.setLabel ("No way, you loose the game! You had " + Globals.getInstance().game.getSnake().getHealth() + " health points.");
        Globals.getInstance().text = gameOver;
        gameOver.show(stage);
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}

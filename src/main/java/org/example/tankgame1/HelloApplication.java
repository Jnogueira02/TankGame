package org.example.tankgame1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.MedPack.MedPack;
import org.example.tankgame1.Environment.Wall.Wall;
import org.example.tankgame1.Environment.Wall.WallFactory;
import org.example.tankgame1.Missile.Missile;
import org.example.tankgame1.Missile.MissileFactory;
import org.example.tankgame1.Tank.*;
import org.example.tankgame1.Tank.Health.HealthBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloApplication extends Application {
    private MissileFactory missileFactory;
    private final WallFactory wallFactory = new WallFactory();
    private final List<Wall> walls = new ArrayList<>();
    private UserTank userTank;
    private HealthBar healthBar;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        Pane gameArena = new Pane();
        gameArena.setPrefSize(800, 400);
        gameArena.setStyle("-fx-background-color: darkseagreen;");
        root.setCenter(gameArena);
        root.setStyle("-fx-background-color: white;");


        Scene scene = new Scene(root, 1000, 500);

        // Create walls
        walls.add(wallFactory.createWall(100, 45, 8, 100));
        walls.add(wallFactory.createWall(230, 50, 100, 8));
        walls.forEach(wall -> gameArena.getChildren().add(wall.getRectangle()));

        // Create user tank
        TankFactory tankFactory = TankFactory.getInstance();
        userTank = (UserTank) tankFactory.createTank(TankType.USER,180, 150);
        gameArena.getChildren().add(userTank.getImageView()); // REFACTOR WITH GAME_ENVIRONMENT???

        // Initialize missile factory
        missileFactory = MissileFactory.getInstance();

        // Initialize the game environment
        GameEnvironment.getInstance().initialize(walls, gameArena, userTank);
        GameEnvironment gameEnvironment = GameEnvironment.getInstance();

        // Create the enemy tanks and add them to a list
        EnemyTank enemyTank1 = (EnemyTank) tankFactory.createTank(TankType.ENEMY, 0, 0);
        gameArena.getChildren().add(enemyTank1.getImageView());
        List<EnemyTank> enemyTanks = new ArrayList<>();
        enemyTanks.add(enemyTank1);
        gameEnvironment.addEnemyTanks(enemyTanks);

        // Add list of tanks to the game environment
        List<Tank> tanks = new ArrayList<>();
        Collections.addAll(tanks, userTank, enemyTank1);
        gameEnvironment.addTanks(tanks);

        // Add MedPacks
        List<MedPack> medPacks = new ArrayList<>();
        MedPack medPack = new MedPack(300, 300);
        gameEnvironment.addMedPack(medPack);

        // Animation Timer for game logic
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(EnemyTank enemyTank: enemyTanks) {
                    enemyTank.move();
                    enemyTank.attemptToShoot();
                    gameEnvironment.updateMissiles();
                }
            }
        };
        gameLoop.start();

        // Handle user input
        scene.setOnKeyPressed(event -> handlePlayerInput(event, userTank, gameArena));


        stage.setScene(scene);
        stage.setTitle("Tank Game");
        stage.setResizable(false);
        stage.show();

    }

    // Respond to user keystrokes (tank movement and firing missiles)
    private void handlePlayerInput(KeyEvent event, UserTank userTank, Pane root){
        switch (event.getCode()) {
            case UP, W -> userTank.moveUp();
            case DOWN, S -> userTank.moveDown();
            case LEFT, A -> userTank.moveLeft();
            case RIGHT, D -> userTank.moveRight();
            case SPACE -> {
                Missile missile = missileFactory.createMissile(userTank);
                root.getChildren().add(missile.getImageView());

                // Continuously move the missile
                AnimationTimer missileTimer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        missile.move();
                    }
                };
                missileTimer.start();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
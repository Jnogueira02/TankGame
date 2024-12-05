package org.example.tankgame1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import java.util.Iterator;
import java.util.List;

public class HelloApplication extends Application {
    private MissileFactory missileFactory;
    private WallFactory wallFactory;
    private TankFactory tankFactory;
    private final List<Wall> walls = new ArrayList<>();
    private final List<EnemyTank> enemyTanks = new ArrayList<>();
    private UserTank userTank;
    private GameEnvironment gameEnvironment;
    private final Pane gameArena = new Pane();

    @Override
    public void start(Stage stage) throws IOException {
        // Create Pane for game
        BorderPane root = new BorderPane();
        gameArena.setPrefSize(800, 400);
        gameArena.setStyle("-fx-background-color: darkseagreen;");
        root.setCenter(gameArena);

        // Initialize health bar
        HealthBar healthBar = new HealthBar();

        // Initialize enemy count
        Label enemyCountLabel = new Label("Enemies: 6");
        root.setBottom(enemyCountLabel);
        new EnemyCountDisplay(enemyCountLabel);

        // Create walls
        wallFactory = WallFactory.getInstance();
        initializeWalls();

        // Create user tank
        tankFactory = TankFactory.getInstance();
        userTank = (UserTank) tankFactory.createTank(TankType.USER,500, 200);
        gameArena.getChildren().add(userTank.getImageView()); // REFACTOR WITH GAME_ENVIRONMENT???
        userTank.addObserver(healthBar);
        root.setTop(healthBar.getProgressBar());

        // Initialize missile factory
        missileFactory = MissileFactory.getInstance();

        // Initialize the game environment
        GameEnvironment.getInstance().initialize(walls, gameArena, userTank);
        gameEnvironment = GameEnvironment.getInstance();

        // Initialize enemy tanks
        initializeEnemyTanks();

        // Add list of tanks to the game environment
        List<Tank> tanks = new ArrayList<>();
        tanks.add(userTank);
        tanks.addAll(enemyTanks);
        gameEnvironment.addTanks(tanks);

        // Add MedPacks
        initializeMedPacks();

        // Animation Timer for game logic
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Iterator<EnemyTank> enemyTankIterator = enemyTanks.iterator();
                while (enemyTankIterator.hasNext()) {
                    EnemyTank enemyTank = enemyTankIterator.next();
                    enemyTank.move();
                    enemyTank.attemptToShoot();
                }
                gameEnvironment.updateMissiles(); // Ensure this method is also safely handling modifications
            }
        };
        gameLoop.start();

        Scene scene = new Scene(root, 1000, 500);
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
    private void initializeEnemyTanks(){
        for(double i = 0; i < 750; i+=374){
            for(double j = 0; j < 350; j+=175){
                EnemyTank enemyTank = (EnemyTank) tankFactory.createTank(TankType.ENEMY, i, j);
                gameArena.getChildren().add(enemyTank.getImageView());
                enemyTanks.add(enemyTank);
            }
        }

        gameEnvironment.addEnemyTanks(enemyTanks);
    }

    private void initializeWalls(){
        walls.add(wallFactory.createWall(100, 45, 8, 100));
        walls.add(wallFactory.createWall(300, 205, 8, 100));
        walls.add(wallFactory.createWall(400, 100, 8, 100));
        walls.add(wallFactory.createWall(250, 300, 8, 100));
        walls.add(wallFactory.createWall(200, 45, 8, 100));
        walls.add(wallFactory.createWall(600, 205, 8, 100));
        walls.add(wallFactory.createWall(800, 100, 8, 100));
        walls.add(wallFactory.createWall(500, 300, 8, 100));

        walls.add(wallFactory.createWall(230, 50, 100, 8));
        walls.add(wallFactory.createWall(100, 250, 100, 8));
        walls.add(wallFactory.createWall(400, 130, 100, 8));
        walls.add(wallFactory.createWall(300, 400, 100, 8));
        walls.add(wallFactory.createWall(460, 50, 100, 8));
        walls.add(wallFactory.createWall(200, 250, 100, 8));
        walls.add(wallFactory.createWall(800, 130, 100, 8));
        walls.add(wallFactory.createWall(600, 400, 100, 8));
        walls.forEach(wall -> gameArena.getChildren().add(wall.getRectangle()));
    }

    private void initializeMedPacks(){
        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){
                MedPack medPack = new MedPack(i*200, j*190);
                gameEnvironment.addMedPack(medPack);
            }
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
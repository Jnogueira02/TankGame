package org.example.tankgame1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.MedPack.MedPack;
import org.example.tankgame1.Environment.Wall.Wall;
import org.example.tankgame1.Environment.Wall.WallFactory;
import org.example.tankgame1.Missile.Missile;
import org.example.tankgame1.Missile.MissileFactory;
import org.example.tankgame1.Tank.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloApplication extends Application {
    private TankFactory tankFactory;
    private MissileFactory missileFactory;
    private WallFactory wallFactory = new WallFactory();
    private List<Wall> walls = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: darkseagreen;");

        stage.setWidth(800);
        stage.setHeight(480);

        Scene scene = new Scene(root);

        // Create walls
        walls.add(wallFactory.createWall(100, 45, 8, 100));
        walls.add(wallFactory.createWall(230, 50, 100, 8));
        walls.forEach(wall -> root.getChildren().add(wall.getRectangle()));

        // Create user tank
        tankFactory = TankFactory.getInstance();
        UserTank userTank = (UserTank) tankFactory.createTank(TankType.USER,180, 150);
        root.getChildren().add(userTank.getImageView()); // REFACTOR WITH GAME_ENVIRONMENT???

        // Initialize missile factory
        missileFactory = MissileFactory.getInstance();

        // Initialize the game environment
        GameEnvironment.getInstance().initialize(walls, root, userTank);
        GameEnvironment gameEnvironment = GameEnvironment.getInstance();

        // Create the enemy tanks and add them to a list
        EnemyTank enemyTank1 = (EnemyTank) tankFactory.createTank(TankType.ENEMY, 0, 0);
        root.getChildren().add(enemyTank1.getImageView());
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

                /*for (Tank tank : gameEnvironment.getTanks()) {
                    for (MedPack medpack : medPacks) {
                        if (medpack.isActive() && tank.intersects(medpack.getXPos(), medpack.getYPos(), medpack.getImageView().getFitWidth(), medpack.getImageView().getFitHeight())) {
                            medpack.applyEffect(tank);
                        }
                    }
                }*/
            }
        };
        gameLoop.start();

        // Handle user input
        scene.setOnKeyPressed(event -> handlePlayerInput(event, userTank, root));


        stage.setScene(scene);
        stage.setTitle("Tank Game");
        stage.setResizable(false);
        stage.show();

    }

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
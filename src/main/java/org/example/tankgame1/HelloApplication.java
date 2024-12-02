package org.example.tankgame1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.tankgame1.Environment.GameEnvironment;
import org.example.tankgame1.Environment.Wall;
import org.example.tankgame1.Environment.WallFactory;
import org.example.tankgame1.Missile.Missile;
import org.example.tankgame1.Missile.MissileFactory;
import org.example.tankgame1.Tank.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private TankFactory tankFactory;
    private MissileFactory missileFactory = new MissileFactory();
    private WallFactory wallFactory = new WallFactory();
    private List<Wall> walls = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: darkseagreen;");

        stage.setWidth(400);
        stage.setHeight(240);

        Scene scene = new Scene(root);

        // Create walls
        walls.add(wallFactory.createWall(100, 45, 8, 100));
        walls.add(wallFactory.createWall(230, 50, 100, 8));
//        GameEnvironment gameEnvironment = GameEnvironment.getInstance();
        for(Wall wall: walls){
            root.getChildren().add(wall.getRectangle());
        }

        // Create user tank
        tankFactory = TankFactory.getInstance();
        UserTank userTank = (UserTank) tankFactory.createTank(TankType.USER,180, 150);
        root.getChildren().add(userTank.getImageView()); // REFACTOR WITH GAME_ENVIRONMENT???

        // Initialize the game environment
        GameEnvironment.getInstance().initialize(walls, root, userTank);

        // Create the enemy tanks
        EnemyTank enemyTank1 = (EnemyTank) tankFactory.createTank(TankType.ENEMY, 0, 0);
        root.getChildren().add(enemyTank1.getImageView());

        // Animation Timer for game logic
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                enemyTank1.move(); // Move the enemy tank on each frame
            }
        };
        gameLoop.start();

        // Handle movement of tank
        scene.setOnKeyPressed((KeyEvent event) -> {
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
        });


        stage.setScene(scene);
        stage.setTitle("Tank Game");
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
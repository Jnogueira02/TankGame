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
import org.example.tankgame1.Tank.Tank;
import org.example.tankgame1.Tank.TankFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private TankFactory tankFactory = new TankFactory();
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
        GameEnvironment.getInstance().initialize(walls, root);
        GameEnvironment gameEnvironment = GameEnvironment.getInstance();
        for(Wall wall: walls){
            root.getChildren().add(wall.getRectangle());
        }

        // Create user tank
        Tank tank = tankFactory.createTank(180, 150, gameEnvironment);
        root.getChildren().add(tank.getImageView());

        // Handle movement of tank
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP, W -> tank.moveUp();
                case DOWN, S -> tank.moveDown();
                case LEFT, A -> tank.moveLeft();
                case RIGHT, D -> tank.moveRight();
                case SPACE -> {
                    Missile missile = missileFactory.createMissile(tank);
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
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
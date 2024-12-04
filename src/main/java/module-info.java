module org.example.tankgame1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tankgame1 to javafx.fxml;
    exports org.example.tankgame1;
    exports org.example.tankgame1.Tank;
    opens org.example.tankgame1.Tank to javafx.fxml;
    exports org.example.tankgame1.Missile;
    opens org.example.tankgame1.Missile to javafx.fxml;
    exports org.example.tankgame1.Environment;
    opens org.example.tankgame1.Environment to javafx.fxml;
    exports org.example.tankgame1.Environment.Explosion;
    opens org.example.tankgame1.Environment.Explosion to javafx.fxml;
    exports org.example.tankgame1.Environment.Wall;
    opens org.example.tankgame1.Environment.Wall to javafx.fxml;
    exports org.example.tankgame1.Environment.Image;
    opens org.example.tankgame1.Environment.Image to javafx.fxml;
}
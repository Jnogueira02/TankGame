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
}
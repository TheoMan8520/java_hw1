module thegame.app.numgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens thegame.app.numgame to javafx.fxml;
    exports thegame.app.numgame;
}
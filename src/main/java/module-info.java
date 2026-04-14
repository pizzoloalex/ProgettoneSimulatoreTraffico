module pizzolo.com.simulatoretraffico {
    requires javafx.controls;
    requires javafx.fxml;


    opens pizzolo.com.simulatoretraffico to javafx.fxml;
    exports pizzolo.com.simulatoretraffico;
    exports pizzolo.com.simulatoretraffico.controller;
    opens pizzolo.com.simulatoretraffico.controller to javafx.fxml;
}
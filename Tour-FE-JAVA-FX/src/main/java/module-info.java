module com.tourdulich.tourdulich {
    requires javafx.controls;
    requires javafx.fxml;
    requires jersey.client;
    requires com.google.gson;
    requires jsr311.api;


    opens com.tourdulich.tourdulich to javafx.fxml;
    exports com.tourdulich.tourdulich;
    exports com.tourdulich.tourdulich.Controller;
    exports com.tourdulich.tourdulich.Entity;
    opens com.tourdulich.tourdulich.Controller to javafx.fxml;
    opens com.tourdulich.tourdulich.Entity to javafx.fxml;
}
module com.example.clientserverapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clientserverapp to javafx.fxml;
    exports com.example.clientserverapp;
}
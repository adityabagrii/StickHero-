module com.example.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;


    opens com.example.stickhero to javafx.fxml;
    exports com.example.stickhero;
}
module org.example.assignment11_eventdrivenprogramming_addremovecircles {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.assignment11_eventdrivenprogramming_addremovecircles to javafx.fxml;
    exports org.example.assignment11_eventdrivenprogramming_addremovecircles;
}
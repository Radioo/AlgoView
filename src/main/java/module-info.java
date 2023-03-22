module com.example.algoview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algoview to javafx.fxml;
    exports com.example.algoview;
}
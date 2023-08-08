module pheonix.smile.pheonixdemobird {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens pheonix.smile.pheonixdemobird to javafx.fxml;
    exports pheonix.smile.pheonixdemobird;

    exports pheonix.smile.pheonixdemobird.layoutmanagers;
    opens pheonix.smile.pheonixdemobird.layoutmanagers to javafx.fxml;
}
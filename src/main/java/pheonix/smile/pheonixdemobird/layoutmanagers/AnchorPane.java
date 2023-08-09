package pheonix.smile.pheonixdemobird.layoutmanagers;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AnchorPane extends Application {
    public static void main(String[] args) {
      launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
       AnchorPane anchorPane = new AnchorPane();

        Label label = new Label("This is an anchor Pane example");

    }
}

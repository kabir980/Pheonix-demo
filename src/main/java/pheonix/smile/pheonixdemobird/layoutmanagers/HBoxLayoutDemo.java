package pheonix.smile.pheonixdemobird.layoutmanagers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class HBoxLayoutDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();

        for (int i =1; i < 20; i++) {
            Button button = new Button("Hello");
            hBox.getChildren().add(button);
        }
        //tilePane.setOrientation(Orientation.VERTICAL);

        Scene scene = new Scene(hBox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}

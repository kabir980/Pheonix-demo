package pheonix.smile.pheonixdemobird.layoutmanagers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StackPaneDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        StackPane stackPane = new StackPane();

        Image image = new Image(StackPaneDemo.class.getResourceAsStream("/Images/image.jpeg"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);

        stackPane.getChildren().add(imageView);

        Label label = new Label("This is an example of StackPane Layout!!!!!!");
        label.setFont(Font.font("Poppins", 20));
        label.setTextFill(Color.WHITE);
        stackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        stackPane.getChildren().add(label);


        Button button = new Button("submit");
        stackPane.getChildren().add(button);

        Scene scene = new Scene(stackPane, 600, 400);

        stage.setScene(scene);
        stage.show();
    }
}

package pheonix.smile.pheonixdemobird.NumberGuessGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApplication extends Application {
    private TextField textField;
    private Label label, hintLabel;
    private Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Login");
        AnchorPane anchorPane = new AnchorPane();

        VBox vBox = new VBox(10); // VBox for center-aligned label
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(10); // HBox for text field and button
        hBox.setAlignment(Pos.CENTER_LEFT); // Aligns elements to the left within the HBox

        hintLabel = new Label();
        hintLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red");

        label = new Label("Enter Your Username");
        label.setStyle("-fx-font-size: 16px");

        textField = new TextField();
        textField.setPrefHeight(40);
        textField.setPrefWidth(200);

        button = new Button("Ok");
        button.setPrefHeight(40);
        button.setPrefWidth(80);

        button.setOnAction(event -> handleOk());

        vBox.getChildren().add(hintLabel);
        vBox.getChildren().add(label);

        hBox.getChildren().add(textField);
        hBox.getChildren().add(button);

        vBox.getChildren().add(hBox);

        anchorPane.getChildren().add(vBox);
        Scene scene = new Scene(anchorPane, 300, 150);

        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleOk();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleOk() {
        String value = textField.getText();
        try {
            if (value.isEmpty()) {
                hintLabel.setText("Please enter the input!!!");
            } else {
                Stage loginStage = (Stage) button.getScene().getWindow();
                loginStage.close();

                GuessNumberApp guessNumberApp = new GuessNumberApp();
                guessNumberApp.start(new Stage());

                textField.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

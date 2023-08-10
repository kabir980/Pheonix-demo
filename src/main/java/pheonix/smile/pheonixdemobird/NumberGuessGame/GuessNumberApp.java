package pheonix.smile.pheonixdemobird.NumberGuessGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class GuessNumberApp extends Application {
    private int randomNumber;
    private Label resultLabel;
    private TextField userGuessTextField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");
        primaryStage.setResizable(false);

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
        userGuessTextField = new TextField();
        userGuessTextField.setPrefWidth(400);
        userGuessTextField.setPrefHeight(40);

        Button guessButton = new Button("Guess");
        guessButton.setOnAction(event -> handleGuess());

        StackPane stackPane = new StackPane();
        Image image = new Image(GuessNumberApp.class.getResourceAsStream("/Images/gaussNumber.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        stackPane.getChildren().add(imageView);



        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, resultLabel);
        gridPane.addRow(1, userGuessTextField);
        gridPane.add(guessButton, 0, 2);
        GridPane.setHalignment(guessButton, javafx.geometry.HPos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);


        stackPane.getChildren().add(gridPane);

        userGuessTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleGuess(); // Call handleGuess() when Enter is pressed in the text field
            }
        });

        userGuessTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("Q")) {
                showExitConfirmation(primaryStage);
            }
        });

        Scene scene = new Scene(stackPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(userGuessTextField.getText());

            if (userGuess == randomNumber) {
                resultLabel.setText("Congratulations!😎😎🤣🤣. You guessed the correct number.");
            } else if (userGuess < randomNumber) {
                resultLabel.setText("Try a higher number.");
            } else {
                resultLabel.setText("Try a lower number.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!!!!. Please enter a valid number.");
        }
    }

    private void showExitConfirmation(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Game");
        alert.setHeaderText("Do you want to exit the Game?");
        alert.setContentText("Press 'Yes' to exit or 'No' to continue playing.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            primaryStage.close(); // Close the stage
        }
    }

    public static void main(String[] args) {

        launch();
    }
}

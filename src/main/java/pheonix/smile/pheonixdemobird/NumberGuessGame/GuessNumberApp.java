package pheonix.smile.pheonixdemobird.NumberGuessGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class GuessNumberApp extends Application {
    private int randomNumber;
    private Label resultLabel;
    private TextField userGuessTextField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        resultLabel = new Label();
        userGuessTextField = new TextField();

        Button guessButton = new Button("Guess");
        guessButton.setOnAction(event -> handleGuess());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(userGuessTextField, guessButton, resultLabel);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(userGuessTextField.getText());

            if (userGuess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed the correct number.");
            } else if (userGuess < randomNumber) {
                resultLabel.setText("Try a higher number.");
            } else {
                resultLabel.setText("Try a lower number.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }


    public static void main(String[] args) {
        launch();
    }
}

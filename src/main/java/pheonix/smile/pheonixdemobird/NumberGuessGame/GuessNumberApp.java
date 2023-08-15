package pheonix.smile.pheonixdemobird.NumberGuessGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;
import java.util.Random;

public class GuessNumberApp extends Application {
    private int randomNumber;
    private int attempts;
    private Label resultLabel;
    private TextField userGuessTextField;
    private Label countLabel;

    private Button guessButton, resetButton, startButton;


    private Timeline countdownTimer;
    private int remainingTime = 30;
    private Label timerLabel;
    private ToggleButton startPauseButton;
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");
        primaryStage.setResizable(false);

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        timerLabel = new Label("Time Remaining: 30 seconds");
        timerLabel.setStyle("-fx-font-size: 16px;");


        resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
        userGuessTextField = new TextField();
        userGuessTextField.setDisable(true);
        userGuessTextField.setPrefWidth(400);
        userGuessTextField.setPrefHeight(40);
        userGuessTextField.setOnMouseClicked(event -> {
            userGuessTextField.selectAll();
        });

        countLabel = new Label("Attempts: 0"); // Initialize the count label
        countLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;"); // Set font size and text color


        guessButton = new Button("Guess");
        guessButton.setPrefWidth(120); // Set the preferred width
        guessButton.setPrefHeight(40); // Set the preferred height
        guessButton.setDisable(true);
        guessButton.setOnAction(event -> handleGuess());

        resetButton = new Button("Reset");
        resetButton.setPrefWidth(120); // Set the preferred width
        resetButton.setPrefHeight(40);
        resetButton.setDisable(true);
        resetButton.setOnAction(event -> handleReset()); // Call handleReset() when Reset button is clicked

        startButton = new Button("Start");
        startButton.setPrefWidth(120); // Set the preferred width
        startButton.setPrefHeight(40);
        startButton.setOnAction(event -> handleStart());

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

        gridPane.add(resetButton, 0, 3); // Add Reset button to the layout
        GridPane.setHalignment(resetButton, HPos.CENTER);

        gridPane.add(countLabel, 0, 4); // Add count label to the layout
        GridPane.setHalignment(countLabel, HPos.CENTER);

        gridPane.add(startButton, 0, 5);
        GridPane.setHalignment(startButton, HPos.CENTER);

        gridPane.add(timerLabel, 0, 6); // Add timer label below the Start button
        GridPane.setHalignment(timerLabel, HPos.CENTER);


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

    private void handleStart() {

//        startPauseButton = new ToggleButton("Start");
//        startPauseButton.setOnAction(event -> handleStartPauseButton());

        guessButton.setDisable(false);
        userGuessTextField.setDisable(false);
        resetButton.setDisable(false);

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        attempts = 0; //reset the attempts

        remainingTime = 30; //Reset remaining time to 30 seconds


        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        countdownTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingTime--;
                    timerLabel.setText("Time Remaining: " + remainingTime + " seconds");
                    if (remainingTime <= 0) {
                        showGameOverDialog(primaryStage);
                        countdownTimer.stop();
                        guessButton.setDisable(true);
                        userGuessTextField.setDisable(true);
                        resetButton.setDisable(true);
                    }
                })
        );
        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();

    }

    private void handleStartPauseButton() {
        if (startPauseButton.isSelected()) {
            startPauseButton.setText("Pause");
            handleStart();
        } else {
            startPauseButton.setText("Resume");
            countdownTimer.pause();
        }
    }

    private void handleReset() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0; //reset the attempts
        remainingTime = 30; //Reset remaining time to 30 seconds
        resultLabel.setText("");
        userGuessTextField.setText("");
        countLabel.setText("Attempts: 0");
    }

    private void handleGuess() {
        try {
            attempts++;
            countLabel.setText("Attempts: " + attempts);

            int userGuess = Integer.parseInt(userGuessTextField.getText());

            if (userGuess == randomNumber) {
                resultLabel.setText("Congratulations!😎😎🤣🤣. You guessed the correct number.");
                showWinDialog(primaryStage);

                // Stop the timer and disable controls
                attempts = 0; //reset the attempts
                countLabel.setText("Attempts: 0");
                countdownTimer.stop();
                remainingTime = 30; //Reset remaining time to 30 seconds
                timerLabel.setText("Time Remaining: " + remainingTime + " seconds");
                guessButton.setDisable(true);
                userGuessTextField.setDisable(true);
                resetButton.setDisable(true);
                resultLabel.setText("");
                userGuessTextField.setText("");


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

    private void showGameOverDialog(Stage primaryStage) {
        Platform.runLater(() -> {
            Alert gameOverAlert = new Alert(Alert.AlertType.INFORMATION);
            gameOverAlert.setTitle("Game Over");
            gameOverAlert.setHeaderText("Time's Up!");
            gameOverAlert.setContentText("You ran out of time. Better luck next time!");
            gameOverAlert.showAndWait();

            guessButton.setDisable(true);
            userGuessTextField.setDisable(true);
            resetButton.setDisable(true);

//            countdownTimer.stop();
//            startPauseButton.setText("Start");
        });
    }

    private void showWinDialog(Stage primaryStage) {

        Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
        winAlert.setTitle("Congratulations!");
        winAlert.setHeaderText("You Win!");
        winAlert.setContentText("You guessed the correct number! Well done!");
        winAlert.showAndWait();

    }


    public static void main(String[] args) {

        launch();
    }
}

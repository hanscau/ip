package snah.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import snah.Snah;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image snahImage = new Image(this.getClass().getResourceAsStream("/images/snah.png"));
    private Snah snah = new Snah();

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getSnahDialog(snah.greet(), snahImage));
    }

    /** Injects the Snah instance */
    public void setSnah(Snah s) {
        snah = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Snah's reply and then appends them to the dialog container. Clears the user
     * input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = snah.getResponse(input);
        if (response.equals(Snah.EXIT_STRING)) {
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getSnahDialog(response, snahImage));
        userInput.clear();
    }
}

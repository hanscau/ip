package snah;

import java.util.Scanner;

import snah.commands.Command;
import snah.commands.List;
import snah.errors.InvalidTaskException;
import snah.errors.ParsingException;
import snah.util.Parser;
import snah.util.Storage;
import snah.util.Ui;

/**
 * Main class for Snah chatbot
 */
public class Snah {

    public static final String EXIT_STRING = "EXIT";

    private Storage storage;
    private TaskList tasksList;
    private Ui ui;

    /**
     * Constructor for Snah chatbot Initializes the required dependencies for the
     * chatbot
     */
    public Snah() {
        storage = new Storage();
        tasksList = new TaskList(storage);
        ui = new Ui();
    }

    /**
     * Main method for Snah chatbot
     * @param args
     */
    public static void main(String[] args) {
    }

    public String getResponse(String input) {
        try {
            Command currentCommand = Parser.getCommand(input);
            String response = currentCommand.execute(tasksList, storage);
            tasksList.save(storage);
            return response;
        } catch (ParsingException e) {
            return e.getMessage();
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    /**
     * Entry point for the CLI chat loop Reads user input and processes the commands
     */
    public void chatLoop() {
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            input = scanner.nextLine();

            ui.start();
            ui.print(getResponse(input));
            ui.end();

            tasksList.save(storage);
        } while (Parser.isExit(input));
        scanner.close();
    }

    /**
     * Greets the user with a welcome message If user is new, a tutorial is also
     * included
     * @return
     */
    public String greet() {
        String response = "Hello! I'm Snah\nWhat can I do for you?\n";
        response += new List("").execute(tasksList, storage);
        return response;
    }
}

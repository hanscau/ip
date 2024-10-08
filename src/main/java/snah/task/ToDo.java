package snah.task;

/**
 * Class to handle ToDo tasks
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s (Todo) %s", printStatus(), getDescription());
    }

    /**
     * Returns the Todo in the format to save to file
     * @return Todo in the format to save to file
     */
    @Override
    public String toSaveFile() {
        return String.format("T;%s;%s", isDone() ? "x" : "", getDescription());
    }

    /**
     * Returns the format to create a new Todo task
     * @return Format description for the Todo task
     */
    public static String getFormatDescription() {
        return "todo <description>";
    }
}

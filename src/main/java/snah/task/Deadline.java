package snah.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return String.format("%s (Deadline: by %s) %s", printStatus(),
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), getDescription());
    }

    /**
     * Returns true if the task contains the keyword, including the 'by' field.
     * 
     * @param keyword
     * @return boolean
     */
    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).contains(keyword);
    }

    public String toSaveFile() {
        return String.format("D:%s:%s:%s", isDone() ? "x" : "", getDescription(), by);
    }

}

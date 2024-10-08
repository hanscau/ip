package snah.commands;

import snah.TaskList;
import snah.errors.InvalidTaskException;
import snah.errors.ParsingException;
import snah.task.Task;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Delete command to delete a task from the task list
 */
public class Delete extends Command {
    public Delete(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException, InvalidTaskException {
        int taskIndex = Parser.getTaskIndex(getInput());

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskException(taskIndex);
        }

        Task deletedTask = tasks.remove(taskIndex);
        tasks.save(storage);

        return String.format("Alright, task is removed\n  %s", deletedTask);
    }

}

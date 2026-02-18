package duke;

import duke.exception.DukeException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;

/**
 * Enum representing the supported commands of the chatbot.
 * Each command has an associated method to execute the command's functionality.
 */
public enum Command {
    BYE {
        @Override
        String execute(Verse bot, String args) {
            return bot.exitProgram();
        }
    },
    DEADLINE {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException {
            return bot.createDeadlineTask(args);
        }
    },
    DELETE {
        @Override
        String execute(Verse bot, String args) throws TaskNotFoundException {
            return bot.deleteTask(Integer.parseInt(args));
        }
    },
    EVENT {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException {
            return bot.createEventTask(args);
        }
    },
    FIND {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException {
            return bot.findTasks(args);
        }
    },
    LIST {
        @Override
        String execute(Verse bot, String args) throws TaskNotFoundException {
            return bot.listTasks();
        }
    },
    MARK {
        @Override
        String execute(Verse bot, String args) throws TaskNotFoundException {
            return bot.markTaskAsDone(Integer.parseInt(args));
        }
    },
    TODO {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException {
            return bot.createTodoTask(args);
        }
    },
    UNMARK {
        @Override
        String execute(Verse bot, String args) throws TaskNotFoundException {
            return bot.unmarkTaskAsDone(Integer.parseInt(args));
        }
    },
    EDIT {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException, TaskNotFoundException {
            return bot.editTask(args);
        }
    };

    abstract String execute(Verse bot, String args) throws DukeException;
}

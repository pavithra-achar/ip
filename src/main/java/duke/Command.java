package duke;

import duke.exception.DukeException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;

public enum Command {
    BYE {
        @Override
        String execute(Verse bot, String args) {
            return bot.exitProgram();
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
    UNMARK {
        @Override
        String execute(Verse bot, String args) throws TaskNotFoundException {
            return bot.unmarkTaskAsDone(Integer.parseInt(args));
        }
    },
    DELETE {
        @Override
        String execute(Verse bot, String args) throws TaskNotFoundException {
            return bot.deleteTask(Integer.parseInt(args));
        }
    },
    TODO {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException {
            return bot.createTodoTask(args);
        }
    },
    DEADLINE {
        @Override
        String execute(Verse bot, String args) throws MissingParameterException {
            return bot.createDeadlineTask(args);
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
    };

    abstract String execute(Verse bot, String args) throws DukeException;
}

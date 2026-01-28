public enum Command {
    BYE {
        @Override
        void execute(Verse bot, String args) {
            bot.exitProgram();
        }
    },
    LIST {
        @Override
        void execute(Verse bot, String args) throws TaskNotFoundException {
            bot.listTasks();
        }
    },
    MARK {
        @Override
        void execute(Verse bot, String args) throws TaskNotFoundException {
            bot.markTaskAsDone(Integer.parseInt(args));
        }
    },
    UNMARK {
        @Override
        void execute(Verse bot, String args) throws TaskNotFoundException {
            bot.unmarkTaskAsDone(Integer.parseInt(args));
        }
    },
    DELETE {
        @Override
        void execute(Verse bot, String args) throws TaskNotFoundException {
            bot.deleteTask(Integer.parseInt(args));
        }
    },
    TODO {
        @Override
        void execute(Verse bot, String args) throws MissingParameterException {
            bot.createTodoTask(args);
        }
    },
    DEADLINE {
        @Override
        void execute(Verse bot, String args) throws MissingParameterException {
            bot.createDeadlineTask(args);
        }
    },
    EVENT {
        @Override
        void execute(Verse bot, String args) throws MissingParameterException {
            bot.createEventTask(args);
        }
    };

    abstract void execute(Verse bot, String args) throws DukeException;
}

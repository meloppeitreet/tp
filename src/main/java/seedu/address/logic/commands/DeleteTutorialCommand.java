package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;

/**
 * Deletes a tutorial to the address book.
 */
public class DeleteTutorialCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "Usage: tutorial delete TUTORIAL_NAME";

    public static final String MESSAGE_SUCCESS = "Successfully deleted %1$s";
    public static final String MESSAGE_INVALID_NAME = """
                    The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)""";
    public static final String MESSAGE_TUTORIAL_DOES_NOT_EXIST = "%1$s doesn't exist";

    private final Tutorial toDelete;

    /**
     * Creates a {@link DeleteTutorialCommand} to add the specified {@code Tutorial}
     */
    public DeleteTutorialCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toDelete = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTutorial(toDelete)) {
            throw new CommandException(MESSAGE_TUTORIAL_DOES_NOT_EXIST.formatted(toDelete));
        }

        model.deleteTutorial(toDelete);
        return new CommandResult(MESSAGE_SUCCESS.formatted(toDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTutorialCommand otherDeleteCommand)) {
            return false;
        }

        return toDelete.equals(otherDeleteCommand.toDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toDelete", toDelete).toString();
    }
}

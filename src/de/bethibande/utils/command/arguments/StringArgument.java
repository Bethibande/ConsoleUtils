package de.bethibande.utils.command.arguments;

import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

public class StringArgument extends Argument<String> {

    public StringArgument(String name, String description, boolean required) {
        super(name, description, ArgumentType.STRING, required);
    }

    public StringArgument(String name, boolean required) {
        super(name, ArgumentType.STRING, required);
    }

    @Override
    public String getValue(CommandArguments arguments) {
        return arguments.getArgument(super.getName());
    }

    @Override
    public boolean isValidValue(String value) {
        return true;
    }
}

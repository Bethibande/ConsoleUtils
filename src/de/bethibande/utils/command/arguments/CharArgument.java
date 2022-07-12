package de.bethibande.utils.command.arguments;

import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

public class CharArgument extends Argument<Character> {

    public CharArgument(String name, String description, boolean required) {
        super(name, description, ArgumentType.CHAR, required);
    }

    public CharArgument(String name, boolean required) {
        super(name, ArgumentType.CHAR, required);
    }

    @Override
    public Character getValue(CommandArguments arguments) {
        if(!arguments.hasArgument(super.getName())) return null;
        return arguments.getCharacter(super.getName());
    }

    @Override
    public boolean isValidValue(String value) {
        return value.length() == 1;
    }
}

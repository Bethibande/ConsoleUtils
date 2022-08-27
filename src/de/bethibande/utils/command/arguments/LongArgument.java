package de.bethibande.utils.command.arguments;

import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

public class LongArgument extends Argument<Long> {

    public LongArgument(String name, String description, boolean required) {
        super(name, description, ArgumentType.LONG, required);
    }

    public LongArgument(String name, boolean required) {
        super(name, ArgumentType.LONG, required);
    }

    @Override
    public Long getValue(CommandArguments arguments) {
        if(!arguments.hasArgument(super.getName())) return getDefaultValue();
        return arguments.getLong(super.getName());
    }

    @Override
    public boolean isValidValue(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }
}

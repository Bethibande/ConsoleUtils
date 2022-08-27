package de.bethibande.utils.command.arguments;

import de.bethibande.utils.Arrays;
import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

public class BooleanArgument extends Argument<Boolean> {

    public BooleanArgument(String name, String description, boolean required) {
        super(name, description, ArgumentType.BOOLEAN, required);
    }

    public BooleanArgument(String name, boolean required) {
        super(name, ArgumentType.BOOLEAN, required);
    }

    @Override
    public String[] getAllowedValues() {
        return Arrays.of("true", "false", null);
    }

    @Override
    public Boolean getValue(CommandArguments arguments) {
        if(!arguments.hasArgument(super.getName())) return getDefaultValue();
        return arguments.getBoolean(super.getName());
    }

    @Override
    public boolean isValidValue(String value) {
        if(value == null) return true;
        return value.toLowerCase().matches("true|false");
    }
}

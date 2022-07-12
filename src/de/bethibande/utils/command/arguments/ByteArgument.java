package de.bethibande.utils.command.arguments;

import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

public class ByteArgument extends Argument<Byte> {

    public ByteArgument(String name, String description, boolean required) {
        super(name, description, ArgumentType.BYTE, required);
    }

    public ByteArgument(String name, boolean required) {
        super(name, ArgumentType.BYTE, required);
    }

    @Override
    public Byte getValue(CommandArguments arguments) {
        if(!arguments.hasArgument(super.getName())) return null;
        return arguments.getByte(super.getName());
    }

    @Override
    public boolean isValidValue(String value) {
        try {
            Byte.parseByte(value);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

}

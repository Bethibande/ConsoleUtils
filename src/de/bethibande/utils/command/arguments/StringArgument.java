package de.bethibande.utils.command.arguments;

import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

public class StringArgument extends Argument<String> {

    private boolean allowSpace = true;

    public StringArgument(String name, String description, boolean required) {
        super(name, description, ArgumentType.STRING, required);
    }

    /**
     * Returns true if the string is allowed to contain space symbols, <br>
     * strings with space symbols must be defined as 'command -argument "text text"' instead of 'command -argument text'.<br>
     * @return are space symbols allowed or not
     */
    public boolean isAllowSpace() {
        return allowSpace;
    }

    /**
     * @see #isAllowSpace()
     */
    public void setAllowSpace(boolean allowSpace) {
        this.allowSpace = allowSpace;
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
        return allowSpace || !value.contains(" ");
    }
}

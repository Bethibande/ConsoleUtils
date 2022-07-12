package de.bethibande.utils.command;

import de.bethibande.utils.Arrays;
import de.bethibande.utils.logger.Logger;

import java.util.Collection;
import java.util.HashMap;

public class ArgumentMap {

    private final HashMap<String, Argument> arguments = new HashMap<>();

    public Collection<Argument> getArguments() {
        return arguments.values();
    }

    public boolean isEmpty() {
        return arguments.isEmpty();
    }

    public boolean isNotEmpty() {
        return !arguments.isEmpty();
    }

    public boolean hasArgument(String arg) {
        return arguments.containsKey(arg);
    }

    public boolean containsArgument(String arg) {
        return hasArgument(arg);
    }

    public void addArgument(Argument arg) {
        arguments.put(arg.getName(), arg);
    }

    public boolean hasAllRequiredArguments(CommandArguments args) {
        return arguments.values().stream().filter(Argument::isRequired).filter(arg -> !args.hasArgument(arg)).findAny().isEmpty();
    }

    public boolean matchArgumentTypes(CommandArguments args) {

        for(Argument arg : arguments.values()) {
            if(!args.hasArgument(arg)) continue;

            String val = args.getArgument(arg.getName());
            ArgumentType type = arg.getType();

            if(!arg.isValidValue(val)) {
                Logger.getLogger().logError("Command argument type mismatch argument '" + arg.getName() + "', required type '" + type + "'!");
                return false;
            }

            if(arg.getAllowedValues() != null && !Arrays.contains(Arrays.toLowerCase(arg.getAllowedValues()), val.toLowerCase())) {
                Logger.getLogger().logError("Value '" + val + "' not allowed for argument '" + arg.getName() + "'!");
                return false;
            }

        }

        return true;
    }

}

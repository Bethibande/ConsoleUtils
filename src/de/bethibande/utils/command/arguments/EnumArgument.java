package de.bethibande.utils.command.arguments;

import de.bethibande.utils.command.Argument;
import de.bethibande.utils.command.ArgumentType;
import de.bethibande.utils.command.CommandArguments;

import java.util.Arrays;

public class EnumArgument<T extends Enum<T>> extends Argument<T> {

    private final Class<T> clazz;

    public EnumArgument(String name, Class<T> clazz, String description, boolean required) {
        super(name, description, ArgumentType.ENUM, required);

        this.clazz = clazz;
    }

    public EnumArgument(String name, Class<T> clazz, boolean required) {
        super(name, ArgumentType.ENUM, required);

        this.clazz = clazz;
    }

    @Override
    public T getValue(CommandArguments arguments) {
        if(!arguments.hasArgument(super.getName())) return null;

        String val = arguments.getArgument(super.getName());
        for(T e : clazz.getEnumConstants()) {
            if(e.name().equalsIgnoreCase(val)) return e;
        }
        return null;
    }

    @Override
    public EnumArgument setAllowedValues(String... allowed) {
        throw new RuntimeException("setAllowedValues() is not allowed for EnumArgument.class");
    }

    @Override
    public String[] getAllowedValues() {
        return Arrays.stream(clazz.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    @Override
    public boolean isValidValue(String value) {
        return de.bethibande.utils.Arrays.contains(de.bethibande.utils.Arrays.toLowerCase(getAllowedValues()), value.toLowerCase());
    }
}

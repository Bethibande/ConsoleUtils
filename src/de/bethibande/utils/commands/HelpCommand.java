package de.bethibande.utils.commands;

import de.bethibande.utils.Arrays;
import de.bethibande.utils.StringUtils;
import de.bethibande.utils.command.*;
import de.bethibande.utils.logger.Logger;

public class HelpCommand extends Command {

    private final CommandManager manager;
    private final Logger l;

    public HelpCommand(CommandManager manager, Logger logger) {
        super("help");
        this.manager = manager;
        this.l = logger;

        setDescription("command help, use 'help [command]' for more specific help");
    }

    @Override
    public void execute(CommandArguments args, String command, String name) {
        if(args.getArguments().length == 0) {
            l.logMessage("Help - Commands");

            for(Command cmd : manager.getCommandMap().getUniqueCommands()) {
                String names = cmd.getAliases() != null ? Arrays.join(Arrays.add(cmd.getAliases(), cmd.getCommandName()), "/"): cmd.getCommandName();
                l.logMessage(" " + names + " | " + cmd.getDescription());
            }
        }

        int length = args.getArguments().length;
        Command cmd = null;
        SubCommand sub = null;
        for(int i = 0; i < length; i++) {
            String arg = args.getArguments()[i];
            if(i == 0) {
                cmd = manager.getCommandMap().dispatch(arg);

                if(cmd == null) {
                    l.logError("There is no such command '" + arg + "'");
                    return;
                }

                if(length == 1) {
                    l.logMessage("Help - " + cmd.getCommandName());
                    l.logMessage(" Description: §c" + cmd.getDescription());
                    l.logMessage(" Aliases: §c" + (cmd.getAliases() != null ? "<" + Arrays.join(cmd.getAliases(), "/") + ">": "none"));
                    l.logMessage(" Sub commands: §c" + (cmd.getChildren().isEmpty() ? "none": Arrays.join(cmd.getChildren().stream().map(SubCommand::getCommandName).toArray(String[]::new), ", ")));

                    if(cmd.getArgumentMap().isNotEmpty()) {
                        l.logMessage(" Arguments: ");
                        for(Argument a : cmd.getArgumentMap().getArguments()) {
                            String color = StringUtils.calcColor(a.getName());

                            l.logMessage("  -" + color + a.getName() + "§r | " + a.getDescription());
                            if(a.isRequired()) l.logMessage("   Required: §ayes");
                            if(a.getDefaultValue() != null) l.logMessage("   Default Value: §c" + a.getDefaultValue());
                            l.logMessage("   Type: §c" + a.getType());
                            if(a.getAllowedValues() != null) {
                                l.logMessage("   Values: §c<" + Arrays.join(a.getAllowedValues(), ", ") + ">");
                            }
                        }
                    } else l.logMessage(" Arguments: none");
                }
                continue;
            }

            if(sub == null) {
                sub = cmd.getSubCommand(arg);
            } else sub = sub.getSubCommand(arg);

            if(sub == null) {
                l.logError("No such command '" +  arg + "' -> '" + Arrays.join(args.getArguments(), " ") + "'!");
                return;
            }

            l.logMessage("Help - " + sub.getCommandName());
            l.logMessage(" Description: §c" + sub.getDescription());
            l.logMessage(" Aliases: §c" + (sub.getAliases() != null ? "<" + Arrays.join(sub.getAliases(), "/") + ">": "none"));
            l.logMessage(" Sub commands: §c" + (sub.getChildren().isEmpty() ? "none": Arrays.join(sub.getChildren().stream().map(SubCommand::getCommandName).toArray(String[]::new), ", ")));

            if(sub.getArgumentMap().isNotEmpty()) {
                l.logMessage(" Arguments: ");
                for(Argument a : sub.getArgumentMap().getArguments()) {
                    String color = StringUtils.calcColor(a.getName());

                    l.logMessage("  -" + color + a.getName() + "§r | " + a.getDescription());
                    if(a.isRequired()) l.logMessage("   Required: §ayes");
                    if(a.getDefaultValue() != null) l.logMessage("   Default Value: §c" + a.getDefaultValue());
                    l.logMessage("   Type: §c" + a.getType());
                    if(a.getAllowedValues() != null) {
                        l.logMessage("   Values: §c<" + Arrays.join(a.getAllowedValues(), ", ") + ">");
                    }
                }
            } else l.logMessage(" Arguments: none");
        }
    }
}

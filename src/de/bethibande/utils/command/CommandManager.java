package de.bethibande.utils.command;

import de.bethibande.utils.logger.Logger;

public class CommandManager {

    private final CommandMap commandMap = new CommandMap();

    public void registerCommand(Command command) {
        commandMap.add(command.getCommandName(), command);
    }

    public void dispatch(String command) {
        Command cmd = commandMap.dispatch(command);
        if(cmd == null) {
            Logger.getLogger().logError("There is no such command '" + command + "'!");
            return;
        }

        cmd.dispatch(command);
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}

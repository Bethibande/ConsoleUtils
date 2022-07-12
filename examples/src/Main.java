import de.bethibande.utils.command.CommandDispatcher;
import de.bethibande.utils.command.CommandManager;
import de.bethibande.utils.commands.HelpCommand;
import de.bethibande.utils.logger.Logger;

import java.io.File;

public class Main {

    // console command help:
    // note 'help' command will only work if registered
    // write 'help' to get a list of available commands
    // write 'help {command}' to get more specific help with a certain command, replace "{command}" with the name of the command or even subcommand or alias like 'help print' or 'help prt' or 'help yourCommand yourSubcommand'
    // write 'print -t TEXT' or 'print -t "Hello World!"'
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.init(new File("./log.txt")); // init log file, not needed, if you don't want a log file

        CommandManager commandManager = new CommandManager();
        commandManager.registerCommand(new HelpCommand(commandManager, logger)); // HelpCommand.java is a default help command
        commandManager.registerCommand(new TestCommand());
        commandManager.registerCommand(new StopCommand(logger));

        CommandDispatcher commandDispatcher = new CommandDispatcher(); // Command dispatcher class is a daemon thread
        commandDispatcher.setCommandManager(commandManager); // important
        commandDispatcher.start();

        while(true) {
            // block main thread, to prevent jvm from closing
        }
    }

}

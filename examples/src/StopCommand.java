import de.bethibande.utils.command.Command;
import de.bethibande.utils.command.CommandArguments;
import de.bethibande.utils.command.arguments.EnumArgument;
import de.bethibande.utils.command.arguments.LongArgument;
import de.bethibande.utils.logger.Logger;

import java.util.concurrent.TimeUnit;

public class StopCommand extends Command {

    private final LongArgument timeArgument = new LongArgument("t", false);
    private final EnumArgument<TimeUnit> timeunitArgument = new EnumArgument<>("u", TimeUnit.class, false);

    private final Logger logger;

    public StopCommand(Logger logger) {
        super("stop");

        this.logger = logger;

        setAliases("shutdown");
        addArgument(timeArgument.setDescription("Delay until the shutdown is executed, by default in seconds, timeunit can be overridden using -u argument"));
        addArgument(timeunitArgument.setDescription("Timeunit for the delay argument, default is seconds (s)"));

        setDescription("Shutdown/Stop the server");
    }

    @Override
    public void execute(CommandArguments args, String command, String name) {
        if(args.hasArgument(timeArgument)) {
            TimeUnit tUnit = TimeUnit.SECONDS;

            if(args.hasArgument(timeunitArgument)) {
                tUnit = timeunitArgument.getValue(args);
            }

            Threads.runLater(() -> System.exit(0), TimeUnit.MILLISECONDS.convert(timeArgument.getValue(args), tUnit), "Shutdown delay");

            logger.logMessage("Shutting down in §c" + timeArgument.getValue(args) + " " + tUnit.toString().toLowerCase());

            return;
        }

        logger.logMessage("Good Bye!");
        System.exit(0);
    }
}

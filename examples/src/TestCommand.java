import de.bethibande.utils.command.Command;
import de.bethibande.utils.command.CommandArguments;
import de.bethibande.utils.command.arguments.StringArgument;
import de.bethibande.utils.logger.Logger;

public class TestCommand extends Command {

    private StringArgument text = new StringArgument("t", true); // t = argument name, true = argument required, command won't execute without this argument

    public TestCommand() {
        super("print");
        setAliases("prt");
        setDescription("Test command, prints text in console");

        addArgument(text.setDescription("Text to print"));
    }

    @Override
    public void execute(CommandArguments args, String command, String name) {
        String txt = text.getValue(args);

        Logger.getLogger().logMessage("Print >> \"§9" + txt + "§r\"");
    }
}

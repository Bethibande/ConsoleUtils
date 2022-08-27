import de.bethibande.utils.logger.ConsoleColors;
import de.bethibande.utils.logger.Logger;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        for(int i = 0; i < 256; i++) {
            System.out.println("\u001b[" + i + "m" + "Text #" + i);
        }

        Logger logger = new Logger();
        logger.init(new File("./log.txt"));

        logger.logMessage(ConsoleColors.colorOf(105) + " Test " + ConsoleColors.colorOf(104) + " Test2 " + ConsoleColors.underlineOf(102) + " Test3 §r");
    }

}

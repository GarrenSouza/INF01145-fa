package fbd.application.actions;

import java.io.IOException;

/**
 *
 * @author garren
 */
public class ClearScreenAction implements UIAction {

    @Override
    public void execute() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final IOException e) {
            //  Handle exceptions
        }
    }

}

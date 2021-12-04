package fbd.application.actions;

import fbd.utils.Input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garren
 */
public class ShowHelpAction implements UIAction{

    @Override
    public void execute() {
        StringBuilder message = new StringBuilder();
        
        message.append("#=======[ HELP ]=======#\n");
        message.append("| There are three main functionalities: Run query, edit query and see the activity history during the current session\n");
        message.append("| Queries are presented as:\n");
        message.append("| -> QueryID | SQL query with ? as placeholders for parameters : (parameter as SQL_Type, ... )\n");
        message.append("| Parameters are shown in the order they are replaced when the query is processed\n");
        message.append("| In order to run a query or to update some parameter you need to specify its ID and properly enter the desired information\n");
        message.append("| That's it!\n");
        
        try {
            (new ClearScreenAction()).execute();
            System.out.println(message.toString());
            System.out.print("Press Enter to continue...");
            Input.waitEnterKeyPress();
        } catch (IOException ex) {
            Logger.getLogger(ShowCreditsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

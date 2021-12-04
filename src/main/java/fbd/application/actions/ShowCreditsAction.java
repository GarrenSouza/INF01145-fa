package fbd.application.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garren
 */
public class ShowCreditsAction implements UIAction {

    @Override
    public void execute() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder message = new StringBuilder();
        
        message.append("#=======[ TEAM ]=======#\n");
        message.append("> Made by Garren Souza & Vítor Vargas @ UFRGS | 2021 \n");
        message.append("> Have a great day :)\n");
        try {
            System.out.println(message.toString());
            System.out.println("Press Enter to continue...");
            reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ShowCreditsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

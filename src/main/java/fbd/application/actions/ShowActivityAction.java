package fbd.application.actions;

import fbd.application.Application;
import fbd.utils.Input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garren
 */
public class ShowActivityAction implements UIAction{

    private final Application app;

    public ShowActivityAction(Application app) {
        this.app = app;
    }    
    
    @Override
    public void execute() {
        List<String> activity = this.app.getActivityHistory();
        StringBuilder output = new StringBuilder();
        
        output.append("#=======[ ACTIVITY ]=======#\n");
        activity.forEach(record -> {
            output.append("| > ").append(record).append("\n");
        });
        
        try {
            (new ClearScreenAction()).execute();
            System.out.print(output.toString());
            Input.waitEnterKeyPress();
        } catch (IOException ex) {
            Logger.getLogger(ShowActivityAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

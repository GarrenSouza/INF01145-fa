package fbd.application.actions;

import fbd.application.Application;
import java.util.List;

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
        System.out.print(output.toString());
    }
    
}

package fbd.application.actions;

import fbd.application.Application;
import fbd.database.QueryParameterType;
import fbd.database.QueryParameter;
import fbd.database.exceptions.IllegalParameterUpdate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.sql.Date;

/**
 *
 * @author garren
 */
public class EditQueryAction implements UIAction{

    private final Application app;

    public EditQueryAction(Application app) {
        this.app = app;
    }
    
    @Override
    public void execute() {
        /**
         * List queries
         * Pick a query
         * Update parameters
         */
        String userInput;
        boolean quit = false;
        
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder typeOptions = new StringBuilder();
        typeOptions.append("| Parameter SQL type\n");
        typeOptions.append("| 1. VARCHAR\n");
        typeOptions.append("| 2. INTEGER\n");
        typeOptions.append("| 3. FLOAT\n");
        typeOptions.append("| 4. DATE\n");
        typeOptions.append("| Option: ");
        
        do {
            (new ClearScreenAction()).execute();
            StringBuilder menu = new StringBuilder();
            menu.append("#=======[ EDIT ]=======#\n");
            menu.append("| Avaliable Queries\n");
            this.app.getQueryDescriptions().forEach(pair -> {menu.append("| -> ").append(pair.getValue0()).append(" | ").append(pair.getValue1()).append("\n");});
            menu.append("| 1. Edit Query\n");
            menu.append("| 2. Back\n");
            menu.append("| Option:");
            System.out.print(menu.toString());
            try {
                userInput = reader.readLine();
                int option = Integer.valueOf(userInput);
                switch(option){
                    case 1 -> {
                        System.out.print("| Enter the Query ID: ");
                        String queryID = reader.readLine();
                        System.out.print("| Enter the parameter position (1-based): ");
                        Integer parameterPosition = Integer.valueOf(reader.readLine());
                        System.out.print("| Enter the parameter value: ");
                        String value = reader.readLine();
                        System.out.println(typeOptions.toString());
                        option = Integer.valueOf(reader.readLine());
                        QueryParameter parameter = null;
                        switch(option){
                            case 1 -> {
                                parameter = new QueryParameter(value, QueryParameterType.VARCHAR);
                            }
                            case 2 -> {
                                parameter = new QueryParameter(Integer.valueOf(value), QueryParameterType.INTEGER);
                            }
                            case 3 -> {
                                parameter = new QueryParameter(Float.valueOf(value), QueryParameterType.FLOAT);
                            }
                            case 4 -> {
                                parameter = new QueryParameter(Date.valueOf(value), QueryParameterType.DATE);
                            }
                        }
                        this.app.updateQueryParameter(queryID, parameter, parameterPosition);
                    }
                    case 2 -> quit = true;
                }
            } catch (IOException ex) {
                System.out.println("Some IO stuff went wrong... Let's try again!");
            } catch (IllegalParameterUpdate ex) {
                System.out.println("Something went wrong with some of the updated parameters!");
            }
        } while(!quit);
    }
    
}

package fbd.application.actions;

import fbd.application.Application;
import fbd.database.exceptions.QueryNotFound;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author garren
 */
public class RunQueryAction implements UIAction {

    private final Application app;

    public RunQueryAction(Application app) {
        this.app = app;
    }

    @Override
    public void execute() {
        /**
         * Show Queries Select Query Run selected query Show results
         */
        String userInput;
        boolean quit = false;
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        output.append("#=======[ RUN ]=======#\n");
        output.append("| Avaliable Queries\n");
        this.app.getQueryDescriptions().forEach(pair -> {
            output.append("| -> ").append(pair.getValue0()).append(" | ").append(pair.getValue1()).append("\n");
        });
        output.append("| 1. Run Query\n");
        output.append("| 2. Back\n");
        output.append("| Option:");

        do {
            (new ClearScreenAction()).execute();
            System.out.print(output.toString());
            try {
                userInput = reader.readLine();
                int option = Integer.valueOf(userInput);
                switch (option) {
                    case 1 -> {
                        System.out.print("| Enter the Query ID: ");
                        userInput = reader.readLine();
                        List<String> queryResult = this.app.runDatabaseOperation(userInput);
                        System.out.println("| Query Result:");
                        queryResult.forEach(tuple -> {
                            System.out.println(tuple);
                        });
                        reader.readLine();
                    }
                    case 2 ->
                        quit = true;
                }
            } catch (IOException ex) {
                System.out.println("Some IO stuff went wrong... Let's try again!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Something went wrong with the database!");
            } catch (QueryNotFound ex) {
                System.out.println("Invalid query selected!");
            }
        } while (!quit);
    }

}

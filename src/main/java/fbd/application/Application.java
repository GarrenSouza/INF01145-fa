package fbd.application;

import org.javatuples.Pair;
import fbd.application.actions.ClearScreenAction;
import fbd.application.actions.EditQueryAction;
import fbd.application.actions.RunQueryAction;
import fbd.application.actions.ShowActivityAction;
import fbd.application.actions.ShowCreditsAction;
import fbd.application.actions.UIAction;
import fbd.application.actions.UIActions;
import fbd.database.Database;
import fbd.database.QueryParameterType;
import fbd.database.Query;
import fbd.database.QueryParameter;
import fbd.database.exceptions.IllegalParameterUpdate;
import fbd.database.exceptions.QueryNotFound;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author garren
 */
public class Application {

    private final HashMap<String, Query> operations;
    private final HashMap<UIActions, UIAction> actions;
    private final Database database;

    public Application() throws IllegalParameterUpdate {
        // proper instantiation
        this.database = new Database();
        this.actions = new HashMap<>();
        this.operations = new HashMap<>();

        // proper setup of queries and actions
        // Actions Setup
        this.actions.put(UIActions.CLEAR_SCREEN, new ClearScreenAction());
        this.actions.put(UIActions.EDIT_QUERY, new EditQueryAction(this));
        this.actions.put(UIActions.RUN_QUERY, new RunQueryAction(this));
        this.actions.put(UIActions.SHOW_ACTIVITY, new ShowActivityAction(this));
        this.actions.put(UIActions.SHOW_CREDITS, new ShowCreditsAction());

        // Queries setup
        Query q1 = new Query(new String("SELECT ?, ?, ? from fbd.addresses"));
        q1.setParameter(new QueryParameter("street", QueryParameterType.VARCHAR), 1);
        q1.setParameter(new QueryParameter("CEP", QueryParameterType.VARCHAR), 2);
        q1.setParameter(new QueryParameter("complement", QueryParameterType.VARCHAR), 3);
        this.operations.put("Q1", q1);
    }

    public ResultSet runDatabaseOperation(String queryID) throws SQLException, QueryNotFound {
        if (this.operations.containsKey(queryID)) {
            return database.executeOperation(this.operations.get(queryID));
        }
        throw new QueryNotFound("Query not found!");
    }

    public void updateQueryParameter(String queryID, QueryParameter parameter, Integer parameter_position) throws IllegalParameterUpdate {
        this.operations.get(queryID).setParameter(parameter, parameter_position);
    }

    public List<String> getActivityHistory() {
        return this.database.getActivityHistory();
    }

    public List<Pair<String, String>> getQueryDescriptions() {
        ArrayList<Pair<String, String>> query_descriptions = new ArrayList<>();
        this.operations.entrySet().forEach(entry -> {
            query_descriptions.add(new Pair(entry.getKey(), entry.getValue().toString()));
        });
        return query_descriptions;
    }

    public static void main(String[] args) throws IllegalParameterUpdate, IOException{
        Application app = new Application();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        boolean quit = false;

        StringBuilder menu = new StringBuilder();
        menu.append("#=======[ MAIN APP ]=======#\n");
        menu.append("| > 1. Run Query\n");
        menu.append("| > 2. Edit Query\n");
        menu.append("| > 3. Show Database Activity\n");
        menu.append("| > 4. Team\n");
        menu.append("| > 5. Quit\n");
        menu.append("| > Option: ");

        while (!quit) {
            System.out.print(menu.toString());
            userInput = reader.readLine();

            int option = Integer.valueOf(userInput);
            switch (option) {
                case 1 -> app.actions.get(UIActions.RUN_QUERY).execute();
                case 2 -> app.actions.get(UIActions.EDIT_QUERY).execute();
                case 3 -> app.actions.get(UIActions.SHOW_ACTIVITY).execute();
                case 4 -> app.actions.get(UIActions.SHOW_CREDITS).execute();
                case 5 -> quit = true;
            }
            app.actions.get(UIActions.CLEAR_SCREEN).execute();
        }
    }
}

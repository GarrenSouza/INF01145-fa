package fbd.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author garren
 */
public class Database {
    private final List<String> history = new ArrayList<>();

    public List<String> executeOperation(DatabaseOperation database_op) throws SQLException {
        List<String> query_result = database_op.execute();
        this.history.add(database_op.toString());
        return query_result;
    }
    
    public List<String> getActivityHistory(){
        ArrayList<String> activity = new ArrayList<>();
        this.history.forEach(record -> {
            activity.add(record);
        });
        return activity;
    }
}

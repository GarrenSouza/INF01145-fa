package fbd.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author garren
 */
public interface DatabaseOperation {
    
    public List<String> execute() throws SQLException;
}

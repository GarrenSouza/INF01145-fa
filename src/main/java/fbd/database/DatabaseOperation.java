package fbd.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author garren
 */
public interface DatabaseOperation {
    
    public ResultSet execute() throws SQLException;
}

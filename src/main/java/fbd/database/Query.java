package fbd.database;

import fbd.database.exceptions.IllegalParameterUpdate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author garren
 */
public class Query implements DatabaseOperation {

    private HashMap<Integer, QueryParameter> parameters;
    private final String query;

    public Query(String query) {
        this.query = query;
        this.parameters = new HashMap<>();
    }

    public Query(Query copied_instance) {
        this.parameters = new HashMap<>(copied_instance.parameters);
        this.query = copied_instance.query;
    }

    private PreparedStatement buildStatement() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(this.query);
        for (Map.Entry<Integer, QueryParameter> entry : this.parameters.entrySet()) {
            switch (entry.getValue().getType()) {
                case VARCHAR -> {
                    statement.setString(entry.getKey(), ((String) entry.getValue().getParameter()));
                }
                case INTEGER -> {
                    statement.setInt(entry.getKey(), ((Integer) entry.getValue().getParameter()));
                }
                case FLOAT -> {
                    statement.setFloat(entry.getKey(), ((Float) entry.getValue().getParameter()));
                }
                case DATE -> {
                    statement.setDate(entry.getKey(), ((Date) entry.getValue().getParameter()));
                }
            }
        }
        return statement;
    }

    public Integer countParameters() {
        return (int) this.query.chars().filter(ch -> ch == '?').count();
    }

    public void setParameter(QueryParameter parameter, Integer position) throws IllegalParameterUpdate {
        if (position <= this.countParameters()) {
            this.parameters.put(position, parameter);
        } else {
            throw new IllegalParameterUpdate("Tried to set " + position + "th parameter as: " + parameter.getParameter().toString() + "...");
        }
    }

    public String getQuery() {
        return query;
    }

    @Override
    public ResultSet execute() throws SQLException {
        return this.buildStatement().executeQuery();
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();

        stringRepresentation.append(this.query);
        stringRepresentation.append(" : ").append("( ");
        for (int i = 1; i <= this.parameters.size(); i++) {
            stringRepresentation.append(this.parameters.get(i).toString());
            if (i < this.parameters.size()) {
                stringRepresentation.append(", ");
            }
        }
        stringRepresentation.append(" )");

        return stringRepresentation.toString();
    }

}

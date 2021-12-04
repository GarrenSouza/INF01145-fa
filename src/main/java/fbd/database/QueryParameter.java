/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fbd.database;

/**
 *
 * @author garren
 */
public class QueryParameter {
    Object parameter;
    QueryParameterType type;

    public QueryParameter(Object parameter, QueryParameterType type) {
        this.parameter = parameter;
        this.type = type;
    }

    public Object getParameter() {
        return parameter;
    }

    public QueryParameterType getType() {
        return type;
    }
    
    @Override
    public String toString(){
        return this.parameter.toString() + " as " + this.type.toString();
    }
}

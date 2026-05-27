/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expection;

import java.util.ArrayList;
import java.util.List;
import utility.Query;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class QueryTypeMismatchException extends RuntimeException     {
    public List<Query.Type> expected = new ArrayList<>();
    public QueryTypeMismatchException(Query.Type... types) {
        super("Wrong Type of Query");
        for(Query.Type t : types){
            expected.add(t);
        }
    }
    @Override
    public String toString(){
        String str = "Wrong Type of Query, Expected ";
        for(Query.Type i : expected){
            str = str + i.name()+", ";
        }
        return str;
    }
}

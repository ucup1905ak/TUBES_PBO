/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception.database;

import utility.db.Query;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Farelino Alexander Kim / 240713000
 */


public class QueryTypeMismatchException extends RuntimeException {

    private final Query.Type actual;
    private final List<Query.Type> expected;

    public QueryTypeMismatchException(
            Query.Type actual,
            Query.Type... expected
    ) {
        this.actual = actual;
        this.expected = Arrays.asList(expected);
    }

    public Query.Type getActual() {
        return actual;
    }

    public List<Query.Type> getExpected() {
        return expected;
    }

    @Override
    public String getMessage() {
        return "Query type mismatch. Expected: " +
                expected.stream()
                        .map(Enum::name)
                        .collect(Collectors.joining(", ")) +
                ", but got: " + actual;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{actual=" + actual +
                ", expected=" + expected +
                '}';
    }
}
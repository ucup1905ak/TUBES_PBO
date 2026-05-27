package utility;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farel
 */
public class Query {

    private final StringBuilder sql;
    private final List<Object> parameters;

    public enum Type {
        SELECT, INSERT, UPDATE, DELETE, UNKNOWN
    }
    public Type queryType = Type.UNKNOWN;
//    public static final Type SELECT = Type.SELECT;
//    public static final Type INSERT = Type.INSERT;
//    public static final Type UPDATE = Type.UPDATE;
//    public static final Type DELETE = Type.DELETE;

    public Query() {
        this.sql = new StringBuilder();
        this.parameters = new ArrayList<>();
    }

    public Query select(String... columns) {
        queryType = Type.SELECT;

        sql.append("SELECT ");
        if (columns == null || columns.length == 0) {
            sql.append("* ");
        } else {
            sql.append(String.join(", ", columns)).append(" ");
        }
        return this;
    }

    public Query from(String table) {
        sql.append("FROM ").append(table).append(" ");
        return this;
    }

    public Query where(String condition, Object... values) {
        sql.append("WHERE ").append(condition).append(" ");
        for (Object val : values) {
            parameters.add(val);
        }
        return this;
    }

    public Query and(String condition, Object... values) {
        sql.append("AND ").append(condition).append(" ");
        for (Object val : values) {
            parameters.add(val);
        }
        return this;
    }

    public Query or(String condition, Object... values) {
        sql.append("OR ").append(condition).append(" ");
        for (Object val : values) {
            parameters.add(val);
        }
        return this;
    }

    public Query orderBy(String column, String direction) {
        sql.append("ORDER BY ").append(column).append(" ").append(direction).append(" ");
        return this;
    }

    public Query limit(int limit) {
        sql.append("LIMIT ").append(limit).append(" ");
        return this;
    }

    public Query join(String table, String condition) {
        sql.append("JOIN ").append(table).append(" ON ").append(condition).append(" ");
        return this;
    }

    public Query innerJoin(String table, String condition) {
        sql.append("INNER JOIN ").append(table).append(" ON ").append(condition).append(" ");
        return this;
    }

    public Query leftJoin(String table, String condition) {
        sql.append("LEFT JOIN ").append(table).append(" ON ").append(condition).append(" ");
        return this;
    }

    public Query rightJoin(String table, String condition) {
        sql.append("RIGHT JOIN ").append(table).append(" ON ").append(condition).append(" ");
        return this;
    }

    public Query insertInto(String table, String... columns) {
        queryType = Type.INSERT;

        sql.append("INSERT INTO ").append(table).append(" (")
                .append(String.join(", ", columns)).append(") ");
        return this;
    }

    public Query values(Object... values) {
        sql.append("VALUES (");
        for (int i = 0; i < values.length; i++) {
            sql.append("?");
            if (i < values.length - 1) {
                sql.append(", ");
            }
            parameters.add(values[i]);
        }
        sql.append(") ");
        return this;
    }

    public Query update(String table) {
        queryType = Type.UPDATE;

        sql.append("UPDATE ").append(table).append(" SET ");
        return this;
    }

    public Query set(String assignment, Object value) {
        if (!sql.toString().endsWith("SET ")) {
            sql.append(", ");
        }
        sql.append(assignment).append(" ");
        parameters.add(value);
        return this;
    }

    public Query deleteFrom(String table) {
        queryType = Type.DELETE;

        sql.append("DELETE FROM ").append(table).append(" ");
        return this;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public String build() {
        return sql.toString().trim() + ";";
    }

    @Override
    public String toString() {
        String debugSql = build();

        for (Object param : parameters) {
            debugSql = debugSql.replaceFirst("\\?", java.util.regex.Matcher.quoteReplacement(formatValue(param)));
        }

        return debugSql;
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "NULL";
        } else if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        } else {
            return "'" + value.toString().replace("'", "''") + "'";
        }
    }
}

package utility;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private final StringBuilder sql;
    private final List<Object> parameters;

    public static enum Type {
        SELECT, INSERT, UPDATE, DELETE, UNKNOWN
    }

    public Type queryType = Type.UNKNOWN;

    public Query() {
        this.sql = new StringBuilder();
        this.parameters = new ArrayList<>();
    }

    // ================= SELECT =================
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

    // ================= WHERE =================
    public Query where(String condition, Object... values) {
        sql.append("WHERE ").append(condition).append(" ");
        addParams(values);
        return this;
    }

    public Query and(String condition, Object... values) {
        sql.append("AND ").append(condition).append(" ");
        addParams(values);
        return this;
    }

    public Query or(String condition, Object... values) {
        sql.append("OR ").append(condition).append(" ");
        addParams(values);
        return this;
    }

    // ================= JOIN =================
    public Query join(String table, String condition) {
        sql.append("JOIN ").append(table).append(" ON ").append(condition).append(" ");
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

    // ================= INSERT =================
    public Query insertInto(String table, String... columns) {
        queryType = Type.INSERT;

        sql.append("INSERT INTO ").append(table).append(" (")
                .append(String.join(", ", columns))
                .append(") ");
        return this;
    }

    public Query values(Object... values) {
        sql.append("VALUES (");

        for (int i = 0; i < values.length; i++) {
            sql.append("?");
            if (i < values.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(") ");
        addParams(values);

        return this;
    }

    // ================= UPDATE =================
    public Query update(String table) {
        queryType = Type.UPDATE;
        sql.append("UPDATE ").append(table).append(" SET ");
        return this;
    }

    public Query set(String column, Object value) {
        sql.append(column).append(" = ?, ");
        parameters.add(value);
        return this;
    }

    // ================= DELETE =================
    public Query deleteFrom(String table) {
        queryType = Type.DELETE;
        sql.append("DELETE FROM ").append(table).append(" ");
        return this;
    }

    // ================= UTIL =================
    private void addParams(Object... values) {
        for (Object v : values) {
            parameters.add(v);
        }
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public String build() {
        cleanTrailingComma();

        if (queryType == Type.DELETE && !sql.toString().contains("WHERE")) {
            throw new IllegalStateException("DELETE without WHERE is not allowed");
        }
        String temp = new String(sql);
        for (Object param : parameters) {
            temp = temp.replaceFirst(
                    "\\?",
                    java.util.regex.Matcher.quoteReplacement(formatValue(param))
            );
        }

        return temp.toString().trim() + ";";
    }

    private void cleanTrailingComma() {
        int len = sql.length();
        if (len >= 2 && sql.substring(len - 2).equals(", ")) {
            sql.setLength(len - 2);
        }
    }

    @Override
    public String toString() {
        return build();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "NULL";
        }
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }
        return "'" + value.toString().replace("'", "''") + "'";
    }
}

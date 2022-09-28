package com.github.smuddgge.database.sqlite;

import com.github.smuddgge.console.Console;
import com.github.smuddgge.console.ConsoleColour;
import com.github.smuddgge.database.*;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a sqlite database
 */
public class SQLiteDatabase implements Database {

    /**
     * The path from resources folder
     */
    private final String fileName;

    /**
     * Connection to the sqlite database
     */
    private Connection connection;

    /**
     * Represents if the database should be used
     * Can be set to false if an error occurs
     */
    private boolean usingDatabase;

    /**
     * The list of registered tables
     */
    private final ArrayList<SQLiteTable> tables = new ArrayList<>();

    /**
     * Used to create a connection to a sqlite database
     *
     * @param fileName The name of the database file
     *                 without the extension specified.
     */
    public SQLiteDatabase(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean setup() {
        // Create the directory and file if it doesn't exist
        File file = new File("src/main/resources", File.separator);

        if (file.mkdir()) {
            Console.log("[Database] Created directory for " + this.fileName + ".sqlite3");
        }

        // Try to connect to the database
        try {

            String url = "jdbc:sqlite:" + file.getAbsolutePath() + File.separator + this.fileName + ".sqlite3";

            this.connection = DriverManager.getConnection(url);

            if (this.connection == null) {
                Console.warn("Unable to connect to the database");
                this.usingDatabase = false;
                return false;
            }

        } catch (SQLException exception) {
            Console.warn("Unable to connect to the database");
            exception.printStackTrace();
            this.usingDatabase = false;
            return false;
        }

        this.usingDatabase = true;
        Console.log("[Database] " + ConsoleColour.GREEN + "Connected to the sqlite database");
        return true;
    }

    @Override
    public boolean createTable(Table table) {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS `").append(table.getName()).append("` (");

        // Build the primary keys
        for (Field field : table.getFields(FieldKeyType.PRIMARY)) {
            String fieldType = SQLiteDatabase.getSqliteType(field.getValueType());

            if (fieldType == null) continue;

            builder.append("`{key}` {type} PRIMARY KEY, "
                    .replace("{key}", field.getKey())
                    .replace("{type}", fieldType)
            );
        }

        // Build other fields
        int index = 0;
        for (Field field : table.getFields(FieldKeyType.FIELD)) {
            index++;
            String fieldType = SQLiteDatabase.getSqliteType(field.getValueType());

            if (fieldType == null) continue;

            builder.append("`{key}` {type}"
                    .replace("{key}", field.getKey())
                    .replace("{type}", fieldType)
            );
            if (index != table.getFields(FieldKeyType.FIELD).size()) builder.append(", ");
        }

        // Build the foreign keys
        for (Field field : table.getFields(FieldKeyType.FOREIGN)) {
            String fieldType = SQLiteDatabase.getSqliteType(field.getValueType());

            if (fieldType == null) continue;

            builder.append(", `{key}` {type}"
                    .replace("{key}", field.getKey())
                    .replace("{type}", fieldType)
            );
        }

        for (Field field : table.getFields(FieldKeyType.FOREIGN)) {
            String fieldType = SQLiteDatabase.getSqliteType(field.getValueType());

            if (fieldType == null) continue;

            builder.append(", FOREIGN KEY({key}) REFERENCES {table}({value})"
                    .replace("{key}", field.getKey())
                    .replace("{table}", field.getReferenceTableName())
                    .replace("{value}", field.getReferenceValueName())
            );
        }

        builder.append(");");

        // Execute the statement
        boolean successful = this.executeStatement(builder.toString());

        // Add table
        this.tables.add((SQLiteTable) table);

        return successful;
    }

    @Override
    public SQLiteTable getTable(String name) {
        for (SQLiteTable table : this.tables) {
            if (Objects.equals(table.getName(), name)) return table;
        }
        return null;
    }

    /**
     * Used to execute a statement
     *
     * @param sql Statement to execute
     * @return True if successful
     */
    public boolean executeStatement(String sql) {
        if (!this.usingDatabase) {
            Console.warn("Tried to use the database when not connected");
            return false;
        }

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException exception) {
            Console.warn("Unable to execute statement: " + sql);
            exception.printStackTrace();
            this.usingDatabase = false;
            return false;
        }

        return true;
    }

    /**
     * Used to execute a query and return the results
     *
     * @param sql Statement to execute
     * @return Result set
     */
    public ResultSet executeQuery(String sql) {
        if (!this.usingDatabase) {
            Console.warn("Tried to use the database when not connected");
            return null;
        }

        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException exception) {
            Console.warn("Unable to execute query: " + sql);
            exception.printStackTrace();
            this.usingDatabase = false;
        }

        return null;
    }

    /**
     * Used to turn field value types into sqlite value types
     * For example, 'string' turns into 'text'
     *
     * @param fieldValueType The value type to convert
     * @return String representing the sqlite type
     */
    public static String getSqliteType(FieldValueType fieldValueType) {
        if (fieldValueType == FieldValueType.INTEGER) return "INTEGER";
        if (fieldValueType == FieldValueType.STRING) return "text";
        return null;
    }
}

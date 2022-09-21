package com.github.smuddgge.database.sqlite;

import com.github.smuddgge.console.Console;
import com.github.smuddgge.console.ConsoleColour;
import com.github.smuddgge.database.*;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Represents a sqlite database
 */
public class SQLiteDatabase implements Database {

    /**
     * The path from resources folder
     */
    private String path;

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
    private ArrayList<Table> tables;

    /**
     * Used to create a connection to a sqlite database
     * @param path The path of witch the database file exists
     *             from the resources folder
     */
    public SQLiteDatabase(String path) {
        this.path = path;
    }

    @Override
    public boolean setup() {
        // Create the directory and file if it doesn't exist
        File file = new File("src/main/resources/" + this.path, File.separator);

        if (!file.mkdir()) {
            this.usingDatabase = false;
            return false;
        }

        // Try to connect to the database
        try {

            this.connection = DriverManager.getConnection(file.getAbsolutePath());

            if (this.connection == null) {
                Console.warn("Unable to connect to the database");
                this.usingDatabase = false;
                return false;
            }
        }
        catch (SQLException exception) {
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

        // Build the foreign keys
        for (Field field : table.getFields(FieldKeyType.FOREIGN)) {
            String fieldType = SQLiteDatabase.getSqliteType(field.getValueType());

            if (fieldType == null) continue;

            builder.append("`{key}` {type} FOREIGN KEY, "
                    .replace("{key}", field.getKey())
                    .replace("{type}", fieldType)
            );
        }

        // Build other fields
        for (Field field : table.getFields(FieldKeyType.FIELD)) {
            String fieldType = SQLiteDatabase.getSqliteType(field.getValueType());

            if (fieldType == null) continue;

            builder.append("`{key}` {type}, "
                    .replace("{key}", field.getKey())
                    .replace("{type}", fieldType)
            );
        }

        builder.append(");");

        // Execute the statement
        return this.executeStatement(builder.toString());
    }

    @Override
    public SQLiteTable getTable(String name) {
        return null;
    }

    /**
     * Used to execute a statement
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
        }
        catch (SQLException exception) {
            Console.warn("Unable to execute statement: " + sql);
            exception.printStackTrace();
            this.usingDatabase = false;
            return false;
        }

        return true;
    }

    /**
     * Used to execute a query and return the results
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
        }
        catch (SQLException exception) {
            Console.warn("Unable to execute query: " + sql);
            exception.printStackTrace();
            this.usingDatabase = false;
        }

        return null;
    }

    /**
     * Used to turn field value types into sqlite value types
     * For example, 'string' turns into 'text'
     * @param fieldValueType The value type to convert
     * @return String representing the sqlite type
     */
    public static String getSqliteType(FieldValueType fieldValueType) {
        if (fieldValueType == FieldValueType.INTEGER) return "INTEGER";
        if (fieldValueType == FieldValueType.STRING) return "text";
        return null;
    }
}

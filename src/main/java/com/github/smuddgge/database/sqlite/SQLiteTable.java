package com.github.smuddgge.database.sqlite;

import com.github.smuddgge.database.Field;
import com.github.smuddgge.database.Record;
import com.github.smuddgge.database.Selector;
import com.github.smuddgge.database.Table;

import java.util.ArrayList;

/**
 * <h2>Represents a sqlite table</h2>
 * Tables are used to get ad update records
 */
public abstract class SQLiteTable implements Table {

    /**
     * <h2>The instance of the database</h2>
     * This instance will be used to query the database
     */
    private final SQLiteDatabase database;

    /**
     * Used to register the table with a database
     * @param database The instance of the database to query
     */
    public SQLiteTable(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public abstract String getName();

    @Override
    public abstract ArrayList<Field> getFields();

    @Override
    public ArrayList<Record> getRecord(Selector selector) {
        return null;
    }

    @Override
    public boolean insertRecord(Record record) {
        return false;
    }
}

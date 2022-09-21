package com.github.smuddgge.database.data;

import com.github.smuddgge.database.Record;
import com.github.smuddgge.database.sqlite.SQLiteDatabase;
import com.github.smuddgge.database.sqlite.SQLiteTable;

/**
 * Represents the player table
 */
public class PlayerTable extends SQLiteTable {

    /**
     * Used to register the table with a database
     *
     * @param database The instance of the database to query
     */
    public PlayerTable(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public Record getRecord() {
        return new PlayerRecord();
    }
}

package com.github.smuddgge.tests.database.sqlite;

import com.github.smuddgge.database.data.PlayerTable;
import com.github.smuddgge.database.sqlite.SQLiteDatabase;
import com.github.smuddgge.results.ResultChecker;
import org.junit.jupiter.api.Test;

/**
 * Used to test the sqlite database
 */
public class TestSQLiteDatabase {

    @Test
    public void testCreateTable() throws Exception {
        SQLiteDatabase database = new SQLiteDatabase("testCreateTable");
        database.setup();

        PlayerTable playerTable = new PlayerTable(database);

        new ResultChecker().expect(database.createTable(playerTable), true);
    }
}

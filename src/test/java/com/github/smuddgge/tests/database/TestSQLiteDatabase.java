package com.github.smuddgge.tests.database;

import com.github.smuddgge.database.sqlite.SQLiteDatabase;
import org.junit.jupiter.api.Test;

/**
 * Used to test the sqlite database
 */
public class TestSQLiteDatabase {

    @Test
    public void testCreateTable() {
        SQLiteDatabase database = new SQLiteDatabase("database.sqlite");


    }
}

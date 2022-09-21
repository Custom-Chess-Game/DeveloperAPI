package com.github.smuddgge.tests.database.sqlite;

import com.github.smuddgge.console.Console;
import com.github.smuddgge.database.Record;
import com.github.smuddgge.database.data.PlayerRecord;
import com.github.smuddgge.database.data.PlayerTable;
import com.github.smuddgge.database.sqlite.SQLiteDatabase;
import com.github.smuddgge.results.ResultChecker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Used to test the player table and record
 * with the sqlite database and table
 */
public class TestPlayerTable {

    @Test
    public void testInsertAndGet() throws Exception {
        // Set up the database
        SQLiteDatabase database = new SQLiteDatabase("testPlayerTable");
        database.setup();

        // Create the table if it doesn't exist
        PlayerTable playerTable = new PlayerTable(database);
        database.createTable(playerTable);

        // Create a player record
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.uuid = UUID.randomUUID().toString();
        playerRecord.name = "Smudge";
        playerRecord.joinDate = "2022";

        // Insert / update record in table
        playerTable.insertRecord(playerRecord);

        // Get matching records in database
        ArrayList<Record> results = playerTable.getRecord("uuid", playerRecord.uuid);

        // Get the first result and compare to creation of record
        PlayerRecord firstRecord = (PlayerRecord) results.get(0);

        Console.log("UUID : " + firstRecord.uuid);
        Console.log("Name : " + firstRecord.name);
        Console.log("Date : " + firstRecord.joinDate);

        new ResultChecker()
                .expect(playerRecord.uuid, firstRecord.uuid)
                .expect(playerRecord.name, firstRecord.name)
                .expect(playerRecord.joinDate, firstRecord.joinDate);
    }
}

package com.github.smuddgge.tests.database.sqlite;

import com.github.smuddgge.console.Console;
import com.github.smuddgge.database.Record;
import com.github.smuddgge.database.data.GameRecord;
import com.github.smuddgge.database.data.GameTable;
import com.github.smuddgge.database.data.PlayerRecord;
import com.github.smuddgge.database.data.PlayerTable;
import com.github.smuddgge.database.sqlite.SQLiteDatabase;
import com.github.smuddgge.results.ResultChecker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Used to test the game table and record
 * with the sqlite database and table
 */
public class TestGameTable {

    @Test
    public void testInsertAndGet() throws Exception {
        // Set up the database
        SQLiteDatabase database = new SQLiteDatabase("testGameTable");
        database.setup();

        // Create the tables if they don't exist
        PlayerTable playerTable = new PlayerTable(database);
        database.createTable(playerTable);

        GameTable gameTable = new GameTable(database);
        database.createTable(gameTable);

        // Create a game record
        GameRecord gameRecord = new GameRecord();
        gameRecord.uuid = UUID.randomUUID().toString();
        gameRecord.player1 = UUID.randomUUID().toString();
        gameRecord.player2 = UUID.randomUUID().toString();
        gameRecord.log = "moves[]";
        gameRecord.timeStamp = String.valueOf(System.currentTimeMillis());

        // Insert / update record in table
        gameTable.insertRecord(gameRecord);

        // Get matching records in database
        ArrayList<Record> results = gameTable.getRecord("uuid", gameRecord.uuid);

        // Get the first result and compare to creation of record
        GameRecord firstRecord = (GameRecord) results.get(0);

        Console.log("UUID : " + firstRecord.uuid);
        Console.log("Player 1 : " + firstRecord.player1);
        Console.log("Player 2 : " + firstRecord.player2);

        new ResultChecker()
                .expect(gameRecord.uuid, firstRecord.uuid)
                .expect(gameRecord.player1, firstRecord.player1)
                .expect(gameRecord.player2, firstRecord.player2)
                .expect(gameRecord.timeStamp, firstRecord.timeStamp)
                .expect(gameRecord.log, firstRecord.log);
    }
}

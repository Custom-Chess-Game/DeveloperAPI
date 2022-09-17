package com.github.smuddgge.database;

import java.util.ArrayList;

/**
 * Represents a table in the database
 */
public interface Table {

    /**
     * Used to get the fields the table has
     * @return List of table fields
     */
    ArrayList<Field> getFields();

    /**
     * Used to get records from the database
     * @param selector Tells the database what
     *                 records to look for
     * @return A list of records that match the selector
     */
    ArrayList<Record> getRecord(Selector selector);

    /**
     * Used to insert a record into the table
     * if the record already exists it will be overwritten
     * @param record Record to insert
     * @return True if inserted successfully
     */
    boolean insertRecord(Record record);
}

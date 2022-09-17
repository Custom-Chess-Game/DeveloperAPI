package com.github.smuddgge.database;

/**
 * Represents a field in the database
 */
public class Field {

    /**
     * The type of field
     * This is used when creating the table so the database
     * knows what type of value will be held
     */
    private FieldType fieldType;

    private String key;
    private Object value;

    /**
     * Used to create a new field
     * @param key The key of the field
     * @param fieldType The type of value that the field will contain
     */
    public Field(String key, FieldType fieldType) {
        this.key = key;
        this.fieldType = fieldType;
    }

    public FieldType getFieldType() {
        return this.fieldType;
    }

    public String getKey() {
        return this.key;
    }
}

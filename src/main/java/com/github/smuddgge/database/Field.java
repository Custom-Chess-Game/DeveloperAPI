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
    private FieldValueType fieldType;
    private FieldKeyType keyType;

    private String key;
    private Object value;

    /**
     * Used to create a new field
     * @param key The key of the field
     * @param valueType The type of value that the field will contain
     */
    public Field(String key, FieldValueType valueType, FieldKeyType keyType) {
        this.key = key;
        this.fieldType = valueType;
        this.keyType = keyType;
    }

    public FieldValueType getValueType() {
        return this.fieldType;
    }

    public FieldKeyType getKeyType() {
        return this.keyType;
    }

    public String getKey() {
        return this.key;
    }
}

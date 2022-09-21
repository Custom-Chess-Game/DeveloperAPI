package com.github.smuddgge.database.data;

import com.github.smuddgge.database.FieldAnnotation;
import com.github.smuddgge.database.FieldKeyType;
import com.github.smuddgge.database.Record;

/**
 * <h2>Represents a player record</h2>
 * Holds information about a player
 */
public class PlayerRecord extends Record {

    @FieldAnnotation(fieldKeyType = FieldKeyType.PRIMARY)
    public String uuid;

    @FieldAnnotation()
    public String name;

    @FieldAnnotation()
    public String joinDate;
}
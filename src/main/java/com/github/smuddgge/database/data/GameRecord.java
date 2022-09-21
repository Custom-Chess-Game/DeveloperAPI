package com.github.smuddgge.database.data;

import com.github.smuddgge.database.FieldAnnotation;
import com.github.smuddgge.database.FieldKeyType;
import com.github.smuddgge.database.ForeignKey;
import com.github.smuddgge.database.Record;

/**
 * <h2>Represents a game record</h2>
 * The game record documents games between two players
 */
public class GameRecord extends Record {

    @FieldAnnotation(fieldKeyType = FieldKeyType.PRIMARY)
    public String uuid;

    @FieldAnnotation
    public String log;

    @FieldAnnotation
    public String timeStamp;

    @FieldAnnotation(fieldKeyType = FieldKeyType.FOREIGN)
    @ForeignKey(tableReferenceName = "Player", tableReferenceValue = "uuid")
    public String player1;

    @FieldAnnotation(fieldKeyType = FieldKeyType.FOREIGN)
    @ForeignKey(tableReferenceName = "Player", tableReferenceValue = "uuid")
    public String player2;
}

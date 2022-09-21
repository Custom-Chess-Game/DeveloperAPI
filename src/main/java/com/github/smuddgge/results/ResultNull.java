package com.github.smuddgge.results;

/**
 * Used in a {@link ResultChecker} to check
 * if a result is null
 */
public class ResultNull implements Result {

    @Override
    public boolean check(Object value) {
        return value == null;
    }
}

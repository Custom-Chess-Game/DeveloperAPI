package com.github.smuddgge.results;

import java.util.Objects;

/**
 * Represents a result
 */
public class ResultChecker {

    /**
     * Used to check a {@link Result} type
     * @param value Value to check
     * @param result Result to check against
     * @return This instance to chain multiple checks
     */
    public ResultChecker expect(Object value, Result result) throws Exception {
        if (!result.check(value))
            throw new Exception(value + " != " + result.getClass().getName());
        return this;
    }

    /**
     * Used to check an exact value
     * @param value Value to check
     * @param match Value to match
     * @return This instance to chain multiple checks
     */
    public ResultChecker expect(Object value, Object match) throws Exception {
        if (!Objects.equals(value, match))
            throw new Exception(value + " != " + match);
        return this;
    }
}

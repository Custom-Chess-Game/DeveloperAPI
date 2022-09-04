package com.github.smuddgge.results;

interface Result {

    /**
     * Used to check a value
     * @return True if correct
     */
    boolean check(Object value);
}

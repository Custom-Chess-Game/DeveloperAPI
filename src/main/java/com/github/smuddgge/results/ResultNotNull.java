package com.github.smuddgge.results;

public class ResultNotNull implements Result {

    @Override
    public boolean check(Object value) {
        return value != null;
    }
}

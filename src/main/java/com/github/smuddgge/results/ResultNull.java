package com.github.smuddgge.results;

public class ResultNull implements Result {

    @Override
    public boolean check(Object value) {
        return value == null;
    }
}

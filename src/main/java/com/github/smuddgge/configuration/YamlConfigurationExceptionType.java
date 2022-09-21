package com.github.smuddgge.configuration;

/**
 * Represents yaml configuration exception types
 */
public enum YamlConfigurationExceptionType {
    PATH_DOESNT_EXIST("Path does not exist: <var>"),
    KEY_NOT_STRING("Path key is not a string: <var>"),
    WRONG_TYPE("This path contains the wrong type: <var>");

    /**
     * Represents the error message
     */
    private final String message;

    /**
     * Used to create a new enum
     *
     * @param message The error message
     */
    YamlConfigurationExceptionType(String message) {
        this.message = message;
    }

    /**
     * Used to get the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Used to get the message and replace the placeholder with a variable
     *
     * @param variable To replace with the placeholder
     * @return The message formatted with the variable
     */
    public String getMessage(String variable) {
        return this.message.replace("<var>", variable);
    }
}

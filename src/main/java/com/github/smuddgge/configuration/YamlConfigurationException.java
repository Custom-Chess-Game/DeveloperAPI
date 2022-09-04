package com.github.smuddgge.configuration;

/**
 * Represents a general yaml configuration error
 */
public class YamlConfigurationException extends RuntimeException {

    /**
     * Used to create a new yaml configuration exception
     * @param type The type of exception
     */
    public YamlConfigurationException(YamlConfigurationExceptionType type) {
        super(type.getMessage());
    }

    /**
     * Used to create a new yaml configuration exception
     * @param type The type of exception
     * @param variable Variable to replace if it has a placeholder
     */
    public YamlConfigurationException(YamlConfigurationExceptionType type, String variable) {
        super(type.getMessage(variable));
    }
}

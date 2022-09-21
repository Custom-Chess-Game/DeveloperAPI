package com.github.smuddgge.configuration;

import java.util.List;

/**
 * Represents a configs options
 */
public interface ConfigurationSelection {

    /**
     * Used to set a value to the config
     *
     * @param path  The location of the key
     * @param value The value to set
     */
    void set(String path, Object value);

    /**
     * Used to get an object
     *
     * @param path The location of the key
     *             The path can use dots to look into sections
     * @return Null or object value
     */
    Object get(String path) throws YamlConfigurationException;

    /**
     * Used to get a path
     *
     * @param path The location of the key
     * @return List
     * Null if path doesn't exist
     * Null if value is not a string
     */
    List<?> getList(String path);

    /**
     * Used to get a string from the config
     *
     * @param path The location of the key
     * @return String value
     * Null if path doesn't exist
     * Null if value is not a string
     */
    String getString(String path);

    /**
     * Used to get an array of strings from the config
     *
     * @param path The location of the key
     * @return List of strings
     * Null if path doesn't exist
     * Null if value is not an integer array
     */
    List<String> getStringList(String path);

    /**
     * Used to get an integer from the config
     *
     * @param path The location of the key
     * @return Int value
     * -1 if path doesn't exist
     * -1 if value is not a string
     */
    int getInt(String path);

    /**
     * Used to get an array of integers from the config
     *
     * @param path The location of the key
     * @return List of integers
     * Null if path doesn't exist
     * Null if value is not an integer array
     */
    List<Integer> getIntList(String path);
}

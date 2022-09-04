package com.github.smuddgge.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a level or selection in the configuration
 */
public class YamlConfigurationSection implements ConfigurationSelection {

    /**
     * Represents the configuration section
     */
    private final Map<String, Object> data;

    /**
     * Used to create a configuration section
     * @param data The data in the config
     */
    public YamlConfigurationSection(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Used to get a configuration section
     * @return YamlConfigurationSection
     *         Error if path does not exist
     *         Null if not a configuration section
     */
    public YamlConfigurationSection getSection(String path) {
        if (!(this.get(path) instanceof Map)) return null;

        Map<?, ?> unknownMap = (Map<?, ?>) this.get(path);
        Map<String, Object> knownMap = new HashMap<>();

        for (Map.Entry<?, ?> entry : unknownMap.entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                throw new YamlConfigurationException(YamlConfigurationExceptionType.KEY_NOT_STRING, path);
            }
            knownMap.put((String) entry.getKey(), entry.getValue());
        }

        return new YamlConfigurationSection(knownMap);
    }

    @Override
    public void set(String path, Object value) {
        this.data.put(path, value);
    }

    @Override
    public Object get(String path) {
        if (this.get(path) == null) {
            throw new YamlConfigurationException(YamlConfigurationExceptionType.PATH_DOESNT_EXIST, path);
        }

        // If the path is within a different section
        if (path.contains(".")) {
            String key = path.split("\\.")[0];
            return this.getSection(key).get(path.replace(key + ".", ""));
        }

        return this.data.get(path);
    }

    @Override
    public List<?> getList(String path) {
        if (this.get(path) instanceof List) return (List<?>) this.get(path);
        return null;
    }

    @Override
    public String getString(String path) {
        if (this.get(path) instanceof String) return (String) this.get(path);
        return null;
    }

    @Override
    public List<String> getStringList(String path) {
        List<?> unknownList = this.getList(path);
        List<String> knownList = new ArrayList<>();

        for (Object item : unknownList) {
            if (!(item instanceof String)) {
                throw new YamlConfigurationException(YamlConfigurationExceptionType.WRONG_TYPE, path);
            }
            knownList.add((String) item);
        }

        return knownList;
    }

    @Override
    public int getInt(String path) {
        if (this.get(path) instanceof Integer) return (Integer) this.get(path);
        return -1;
    }

    @Override
    public List<Integer> getIntList(String path) {
        List<?> unknownList = this.getList(path);
        List<Integer> knownList = new ArrayList<>();

        for (Object item : unknownList) {
            if (!(item instanceof Integer)) {
                throw new YamlConfigurationException(YamlConfigurationExceptionType.WRONG_TYPE, path);
            }
            knownList.add((Integer) item);
        }

        return knownList;
    }
}

package com.github.smuddgge.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Represents a config file
 */
public class YamlConfiguration {

    /**
     * The configuration file
     */
    private final File file;

    /**
     * Data loaded from the yaml
     */
    private Map<String, Object> data;

    /**
     * Creates a new {@link YamlConfiguration}
     */
    public YamlConfiguration(String fileName) {

        // Get config file
        this.file = new File("src/main/resources/" + fileName + ".yml");

        // Create file if it doesn't exist
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Loads the configuration file to the yaml instance
     */
    public void load() {
        try {
            InputStream inputStream = new FileInputStream(this.file);

            Yaml yaml = new Yaml();
            this.data = yaml.load(inputStream);

        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Used to get the config data
     * @return Yaml configuration section
     */
    public YamlConfigurationSection get() {
        return new YamlConfigurationSection(this.data);
    }
}

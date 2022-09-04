package com.github.smuddgge.tests.configuration;

import com.github.smuddgge.configuration.YamlConfiguration;
import com.github.smuddgge.results.ResultChecker;
import com.github.smuddgge.results.ResultInstanceOf;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestYamlConfiguration {

    @Test
    public void testGet() throws Exception {
        YamlConfiguration yamlConfiguration = new YamlConfiguration("tests/YamlExample.yaml");
        yamlConfiguration.load();

        new ResultChecker()
                .expect(yamlConfiguration.get().getString("parent.key1"), "value")
                .expect(yamlConfiguration.get().getList("parent.key2"), new ResultInstanceOf(List.class));
    }
}

package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        /*assertThat(config.value("key1")).isEqualTo(null);*/
        assertThat(config.value("key2")).isEqualTo(null);
        assertThat(config.value("key3")).isEqualTo("value3");
        assertThat(config.value("key4")).isEqualTo("value4=v2");
        assertThat(config.value("key5")).isEqualTo("value5=");
    }

    @Test
    void whenApp() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
        assertThat(config.value("hibernate.connection.password"))
                .isEqualTo("password");
    }
}
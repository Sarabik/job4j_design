package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenWithoutKeyComment() {
        String path = "./data/without_key_comment.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not match the required pattern");
    }

    @Test
    void whenWithoutValueComment() {
        String path = "./data/without_value_comment.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not match the required pattern");
    }

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key3")).isEqualTo("value3");
    }

    @Test
    void whenPairsWithComment() {
        String path = "./data/pairs_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key4")).isEqualTo("value4");
        assertThat(config.value("key5")).isEqualTo("value5=v2");
        assertThat(config.value("key6")).isEqualTo("value6=");
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
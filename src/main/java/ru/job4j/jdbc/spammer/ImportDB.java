package ru.job4j.jdbc.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private void validate(String str) {
        if (!str.matches("^.*;.*@.*\\..*;$")) {
            throw new IllegalArgumentException(
                    String.format("line \"%s\" does not match required pattern", str));
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(str -> {
                validate(str);
                String[] array = str.split(";");
                if (array.length != 2) {
                    throw new IllegalArgumentException(
                            String.format("line \"%s\" does not match required pattern", str));
                }
                User user = new User(array[0], array[1]);
                users.add(user);
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            /* create table users */
            try (Statement st = cnt.createStatement()) {
                st.executeUpdate(String.format("CREATE TABLE if not exists users (%s, %s, %s);",
                        "id serial primary key", "name varchar(255)", "email varchar(255)"));
            }
            /* save data to table users */
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer_app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./src/main/resources/dump.txt");
        db.save(db.load());
    }
}
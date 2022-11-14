package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver_class"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection =  DriverManager.getConnection(url, login, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void statement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE if not exists %s();",
                tableName
        );
        statement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE %s;",
                tableName
        );
        statement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type
        );
        statement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        );
        statement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        statement(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static Properties getProperties(String path) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(path)) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(getProperties("table_editor.properties"));
        String table = "table1";
        tableEditor.createTable(table);
        tableEditor.addColumn(table, "names", "text");
        tableEditor.addColumn(table, "surnames", "text");
        tableEditor.addColumn(table, "age", "int");
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.renameColumn(table, "age", "weight");
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.dropColumn(table, "names");
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.dropTable(table);
        tableEditor.createTable(table);
        System.out.println(tableEditor.getTableScheme(table));
    }
}
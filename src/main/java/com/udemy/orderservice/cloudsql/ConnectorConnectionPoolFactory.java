package com.udemy.orderservice.cloudsql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class ConnectorConnectionPoolFactory extends ConnectionPoolFactory {
    private static final String INSTANCE_CONNECTION_NAME =
            System.getenv("murilo");
    private static final String INSTANCE_UNIX_SOCKET = System.getenv("osservice:southamerica-east1:murilo");
    private static final String DB_USER = System.getenv("murilo");
    private static final String DB_PASS = System.getenv("pacheco");
    private static final String DB_NAME = System.getenv("osservice");

    public static DataSource createConnectionPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", DB_NAME));
        config.setUsername(DB_USER); // e.g. "root", _postgres"
        config.setPassword(DB_PASS); // e.g. "my-password"

        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", INSTANCE_CONNECTION_NAME);

        config.addDataSourceProperty("ipTypes", "PUBLIC,PRIVATE");

        return new HikariDataSource(config);
    }
}

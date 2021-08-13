package org.betacraft.factory.database.databases;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.betacraft.factory.database.Database;

public class MySQL implements Database {

    //VARIABLES
    private final String hostname;
    private final int port;
    private final String user;
    private final String password;
    private final String databaseName;

    private HikariDataSource hikariDataSource;


    //CONSTRUCTOR
    public MySQL(String hostname, int port, String user, String password, String databaseName) {

        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;

        this.onLoad();

    }

    //GETTER FUNCTIONS
    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    @Override
    public void onLoad() {

        System.out.println("[Betacraft-Factory] MYSQL: Connecting " + databaseName + " database...");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + databaseName);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);

        this.hikariDataSource = new HikariDataSource(hikariConfig);

        if (!this.hikariDataSource.isRunning()) {

            System.out.println("[Betacraft-Factory] MYSQL: " + databaseName + " is not running!");
            this.hikariDataSource.close();
            return;

        }

        if (this.hikariDataSource.isClosed()) {

            System.out.println("[Betacraft-Factory] MYSQL: " + databaseName + " is closed!");
            this.hikariDataSource.close();

        }


    }

    @Override
    public void onUnload() {

        System.out.println("[Betacraft-Factory] MYSQL: " + databaseName + " closing...");

        this.hikariDataSource.close();

        System.out.println("[Betacraft-Factory] MYSQL: " + databaseName + " successfuly closed.");

    }
}

package org.betacraft.factory.database.databases;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.betacraft.factory.database.Database;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Mongo implements Database {

    //VARIABLES
    private final String hostname;
    private final String user;
    private final String password;
    private final String databaseName;
    private final String collectionName;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    //CONSTURCTOR
    public Mongo(String hostname, String user, String password, String databaseName, String collectionName) {
        this.hostname = hostname;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        this.onLoad();
    }

    //GETTER FUNCTIONS
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }

    @Override
    public void onLoad() {

        System.out.println("[Betacraft-Factory] MONGO: Connecting " + databaseName + " database...");

        try {

            this.mongoClient = new MongoClient(new MongoClientURI("mongodb://" + this.user + ":" + this.password + "@" + this.hostname));

            this.mongoDatabase = mongoClient.getDatabase(databaseName);

            this.mongoCollection = mongoDatabase.getCollection(this.collectionName);

            System.out.println("[Betacraft-Factory] MONGO: " + databaseName + " successfuly connected.");

        } catch (MongoException e) {

            System.out.println("[Betacraft-Factory] MONGO: " + databaseName + " not connected. Error message is " + e.getLocalizedMessage());

            this.mongoClient.close();

        }

    }

    @Override
    public void onUnload() {

        System.out.println("[Betacraft-Factory] MONGO: " + databaseName + " closing...");

        try {

            this.mongoClient.close();
            System.out.println("[Betacraft-Factory] MONGO: " + databaseName + " successfuly closed.");

        } catch (MongoException e) {

            System.out.println("[Betacraft-Factory] MONGO: " + databaseName + " could not be closed.");

        }


    }
}

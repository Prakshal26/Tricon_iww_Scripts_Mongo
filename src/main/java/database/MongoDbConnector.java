
package database;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import pojo.Person;

import java.sql.*;

public class MongoDbConnector {

    public MongoClient connect() {
        MongoClient mongoClient = null;
        try {
          mongoClient = new MongoClient( "localhost" , 27017 );
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return mongoClient;
    }

    public void insertPeople(MongoClient mongoClient, Person person) {

        int id = 0;
        Statement stmt = null;
        try {

            DB database = mongoClient.getDB("iww_data");
            DBCollection collection = database.getCollection("ww_people");

            Gson gson = new Gson();
            BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(person));

            collection.insert(obj);


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}

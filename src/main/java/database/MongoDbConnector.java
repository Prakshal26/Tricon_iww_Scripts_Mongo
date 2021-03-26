
package database;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import pojo.IwwPac;
import pojo.Person;
import pojo.Source;

import java.sql.*;
import java.time.LocalDateTime;

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


    public static IwwPac insertIwwPac(Person person) {

        IwwPac iwwPac = new IwwPac();
        iwwPac.set_createdDate(LocalDateTime.now().toString());
        iwwPac.set_modifiedDate(LocalDateTime.now().toString());
        iwwPac.set_schemaVersion("4.0.1");
        iwwPac.setType("Person");
        iwwPac.setVersion("1.0.0");
        iwwPac.setTitle("Name");
        iwwPac.set_isSellable(false);

        iwwPac.setPerson(person);

        iwwPac.setIdentifier(new Object());
        iwwPac.setAvailability(new Object[]{});

        Source source = new Source();
        source.setType("product");
        source.setSource("EUROPA");

        iwwPac.setSources(source);

        return iwwPac;

    }

    public void insertPeople(MongoClient mongoClient, Person person) {


        int id = 0;
        Statement stmt = null;
        try {


            IwwPac iwwPac = MongoDbConnector.insertIwwPac(person);

            DB database = mongoClient.getDB("EuropaCollectionsNewXML");
            DBCollection collection = database.getCollection("www_person");

            Gson gson = new Gson();
            BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(iwwPac));

            collection.insert(obj);


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}

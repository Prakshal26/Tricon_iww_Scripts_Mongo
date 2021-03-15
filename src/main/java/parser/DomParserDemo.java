package parser;
//CTRL+ALT+L to format code



import com.mongodb.MongoClient;
import database.MongoDbConnector;
import org.w3c.dom.Document;
import pojo.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class DomParserDemo {

    public static void main(String[] args) throws SQLException {

        MongoDbConnector mongoDbConnector = new MongoDbConnector();
        MongoClient mongoClient = null;
        try {
            HashMapParser.abbrConverter();
            mongoClient = mongoDbConnector.connect();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File dir = new File("C:\\Users\\Admin\\IdeaProjects\\x_json\\WhosWho_IgnoreBadIWWList\\WhosWho_IgnoreBadIWWList");

            File [] files = dir.listFiles();
            int file_count =1;
            for(File file : files) {
                if(file.isFile() && file.getName().endsWith(".xml")) {
                    System.out.println("File "+ file_count++ + file.getName());

                    Document doc = dBuilder.parse(file);
                    Person person = ElementParse.parseFiles(doc);

                    mongoDbConnector.insertPeople(mongoClient, person);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            mongoClient.close();
        }
    }
}



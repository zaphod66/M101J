package com.tengen;

import java.net.*;

import com.mongodb.*;

public class HelloWorldMongoDBStyle {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        
        DB database = client.getDB("test");
        DBCollection collection = database.getCollection("things");
        
        DBObject document = collection.findOne();
        
        System.out.println("Document: " + document);
    }
}

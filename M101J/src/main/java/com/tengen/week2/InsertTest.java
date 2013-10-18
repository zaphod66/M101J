package com.tengen.week2;

import java.net.*;

import com.mongodb.*;

public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("insertTest");
        
        DBObject doc = new BasicDBObject("x", 1);
        
        System.out.println("before doc:" + doc);
        
        collection.insert(doc);
        
        System.out.println("after  doc:" + doc);
    }
}

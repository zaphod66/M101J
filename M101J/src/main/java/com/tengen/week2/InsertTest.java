package com.tengen.week2;

import java.net.*;
import java.util.*;

import org.bson.types.*;

import com.mongodb.*;

public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("insertTest");
        
        collection.drop();
        
        DBObject doc1 = new BasicDBObject().append("x", 1);
        DBObject doc2 = new BasicDBObject("_id", new ObjectId()).append("x", 2);
        DBObject doc3 = new BasicDBObject("_id", new ObjectId()).append("x", 3);
        
        System.out.println("before doc:" + doc1);
        System.out.println("before doc:" + doc2);
        System.out.println("before doc:" + doc3);

        collection.insert(Arrays.asList(doc1, doc2));        
        collection.insert(doc3);

        System.out.println("after  doc:" + doc1);
        System.out.println("after  doc:" + doc2);
        System.out.println("after  doc:" + doc3);
    }
}

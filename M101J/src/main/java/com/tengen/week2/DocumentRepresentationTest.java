package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class DocumentRepresentationTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("insertTest");
        
        BasicDBObject doc = new BasicDBObject();
        doc.put("name", "zaphod");
        doc.put("age",  42);
        doc.put("programmer",  true);
        doc.put("languages", Arrays.asList("Java", "Scala", "C++", "C#"));
        doc.put("address", new BasicDBObject()
                            .append("street", "20 Main")
                            .append("town", "Pangratea")
                            .append("zip", 2122869));
        
        System.out.println("before: " + doc);
        collection.insert(doc);
        System.out.println("after:  " + doc);
    }
}

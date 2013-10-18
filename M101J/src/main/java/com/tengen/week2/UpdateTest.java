package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class UpdateTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("UpdateTest");
        
        coll.drop();
        
        List<String> names = Arrays.asList("alice", "bobby", "cathy", "david", "ethan");
        for (String name : names) {
            coll.insert(new BasicDBObject("_id", name));
        }

        coll.update(new BasicDBObject("_id","alice"), new BasicDBObject("age", 24));
        coll.update(new BasicDBObject("_id","alice"), new BasicDBObject("$set", new BasicDBObject("gender", "F")));

        // upsert
        coll.update(new BasicDBObject("_id","frank"), new BasicDBObject("$set", new BasicDBObject("gender", "M")), true, false);
        
        // multi
        coll.update(new BasicDBObject(), new BasicDBObject("$set", new BasicDBObject("title", "Dr.")), false, true);
        
        coll.remove(new BasicDBObject("_id", "david"));
        
        printCollection(coll, new BasicDBObject());
    }
    
    private static void printCollection(DBCollection coll, DBObject query) {
        System.out.println("==================");
        DBCursor cursor = coll.find(query);
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }        
        System.out.println("==================");
    }
}

package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class FindTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("findTest");
        
        coll.drop();
        
        for (int i = 0; i < 10; i++) {
            coll.insert(new BasicDBObject("x", new Random().nextInt(100)));
        }
        
        System.out.println("findOne()");
        DBObject one = coll.findOne();
        System.out.println(one);
        
        System.out.println("find()");
        DBCursor cursor = coll.find();
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }
        
        System.out.println("count()");
        long cnt = coll.count();
        System.out.println(cnt);
    }
}

package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class FindCriteriaTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("findCriteriaTest");
        
        coll.drop();
        
        for (int i = 0; i < 10; i++) {
            coll.insert(new BasicDBObject()
                               .append("x",  new Random().nextInt(2))
                               .append("y",  new Random().nextInt(100))
            );
        }
        
        DBObject query = new BasicDBObject("x", 0);
        
        System.out.println("count()");
        long cnt = coll.count(query);
        System.out.println(cnt);

        System.out.println("find()");
        DBCursor cursor = coll.find(query);
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }        
    }
}

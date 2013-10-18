package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class FieldSelectionTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("FieldSelectionTest");
        
        coll.drop();
        
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            coll.insert(new BasicDBObject()
                               .append("x", rand.nextInt(2))
                               .append("y", rand.nextInt(100))
                               .append("z", rand.nextInt(1000))
            );
        }
        
        DBObject query = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70).get();

        System.out.println("count()");
        long cnt = coll.count(query);
        System.out.println(cnt);

        System.out.println("find()");
        DBCursor cursor = coll.find(query, new BasicDBObject("y", true).append("_id", false).append("z", true));
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

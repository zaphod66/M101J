package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class SortSkipLimitTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("SortSkipLimitTest");
        
        coll.drop();
        
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            coll.insert(new BasicDBObject("_id", i)
                        .append("start", new BasicDBObject("x", rand.nextInt(2))
                                                   .append("y", rand.nextInt(90) + 10))
                        .append("end",   new BasicDBObject("x", rand.nextInt(2))
                                                   .append("y", rand.nextInt(90) + 10))
            );
        }
        
        QueryBuilder builder = QueryBuilder.start();
        DBObject query = builder.get();
        
        DBCursor cursor = coll.find(query).sort(new BasicDBObject("start.x", 1).append("start.y", -1)).skip(2).limit(5);
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

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
        
        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(90);

//        DBObject query = new BasicDBObject()
//                                .append("x", 0)
//                                .append("y", new BasicDBObject("$gt", 10).append("$lt", 90));
        
        System.out.println("count()");
        long cnt = coll.count(builder.get());
        System.out.println(cnt);

        System.out.println("find()");
        DBCursor cursor = coll.find(builder.get());
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

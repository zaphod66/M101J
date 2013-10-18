package com.tengen.week2;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class DotNotationTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("DotNotationTest");
        
        coll.drop();
        
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            coll.insert(new BasicDBObject("_id", i)
                        .append("start", new BasicDBObject("x", rand.nextInt(90) + 10)
                                                   .append("y", rand.nextInt(90) + 10))
                        .append("end",   new BasicDBObject("x", rand.nextInt(90) + 10)
                                                   .append("y", rand.nextInt(90) + 10))
            );
        }
    }
}

package com.tengen.week2;

import java.net.*;

import com.mongodb.*;

public class FindAndModifyTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("UpdateTest");
        
        coll.drop();
        
        final String counterId = "abc";
        int first; 
        int numNeeded;
        
        numNeeded = 2;
        first = getRange(counterId, numNeeded, coll);
        System.out.println("Range: " + first + " - " + ( first + numNeeded - 1));

        numNeeded = 3;
        first = getRange(counterId, numNeeded, coll);
        System.out.println("Range: " + first + " - " + ( first + numNeeded - 1));

        numNeeded = 10;
        first = getRange(counterId, numNeeded, coll);
        System.out.println("Range: " + first + " - " + ( first + numNeeded - 1));

        printCollection(coll, new BasicDBObject());
    }
    
    private static int getRange(String id, int range, DBCollection coll) {
        DBObject doc = coll.findAndModify(
                new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
        return (Integer) doc.get("counter") - range + 1;
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

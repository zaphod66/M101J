package com.tengen.week3;

import java.net.*;
import java.util.*;

import com.mongodb.*;

public class Homework3_1 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB courseDB = client.getDB("school");
        DBCollection coll = courseDB.getCollection("students");

//        > db.students.find({_id:100}).pretty()
//        {
//            "_id" : 100,
//            "name" : "Demarcus Audette",
//            "scores" : [
//                {
//                    "type" : "exam",
//                    "score" : 30.61740640636871
//                },
//                {
//                    "type" : "quiz",
//                    "score" : 14.23233821353732
//                },
//                {
//                    "type" : "homework",
//                    "score" : 31.41421298576332
//                },
//                {
//                    "type" : "homework",
//                    "score" : 30.09304792394713
//                }
//            ]
//        }
        removeLowestHW(coll);

        DBObject student = coll.findOne(new BasicDBObject("_id", 100));
        System.out.println(student);
        
        List<Map> sc = (List<Map>) student.get("scores");
        System.out.println("Scores: " + sc);
        System.out.println("============");
        for (int i = 0; i < sc.size(); i++) {
            Map score = sc.get(i);
            System.out.println("--> " + score);
        }
    }
    
    // remove the homework with the lowest score from each student
    private static void removeLowestHW(DBCollection coll) {
        DBCursor cursor = null;
        try {
            cursor = coll.find();
            
            while (cursor.hasNext()) {
                DBObject curStudent = cursor.next();
                
                @SuppressWarnings("unchecked")
                List<Map> scores = (List<Map>) curStudent.get("scores");
                int removeIdx = -1;
                double min = 100;
                for (int i = 0; i < scores.size(); i++) {
                    Map score = scores.get(i);
                    if (score.get("type").equals("homework") && (Double)score.get("score") < min) {
                        min = (Double)score.get("score");
                        removeIdx = i;
                    }                
                }
                scores.remove(removeIdx);
                coll.update(new BasicDBObject("_id", curStudent.get("_id")),
                        new BasicDBObject("$set", new BasicDBObject("scores", scores)),true,false);

            }
        } finally {
            cursor.close();
        }
    }
}

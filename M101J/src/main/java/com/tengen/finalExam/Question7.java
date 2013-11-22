package com.tengen.finalExam;

import java.net.*;

import com.mongodb.*;

public class Question7 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient c =  new MongoClient(new MongoClientURI("mongodb://localhost"));
        DB db = c.getDB("q7");
        DBCollection images = db.getCollection("images");
        DBCollection albums = db.getCollection("albums");
        
        // remove orphan images
        DBCursor imCursor = images.find();    // find all images
        try {
            while (imCursor.hasNext()) {
                DBObject image = imCursor.next();
                Integer id = (Integer) image.get("_id");
                
                DBObject album = albums.findOne(new BasicDBObject("images", id));
                if (album == null) {    // no album found -> remove image
                    System.out.println("Removing : " + image.get("_id"));
                    images.remove(image);
                }
            }
        } finally {
            imCursor.close();
        }
        
        System.out.println("There are " + images.count() + " images left.");
        c.close();
    }
}

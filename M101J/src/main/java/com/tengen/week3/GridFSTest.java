package com.tengen.week3;

import java.io.*;
import java.util.*;

import com.mongodb.*;
import com.mongodb.gridfs.*;

public class GridFSTest {
    public static void main(String[] args) throws IOException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB courseDB = client.getDB("course");
        
        FileInputStream inputStream = null;
        
        GridFS pdfs = new GridFS(courseDB, "pdfs");
        
        try {
            inputStream = new FileInputStream("Concurrency.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        GridFSInputFile pdf = pdfs.createFile(inputStream, "Concurrency.pdf");
        
        BasicDBObject meta = new BasicDBObject("description", "Concurreny in databases");
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Book");
        tags.add("Database");
        tags.add("Concurrency");
        meta.append("tags", tags);
        
        pdf.setMetaData(meta);
        pdf.save();
        
        System.out.println("ObjectId in Files Collection: " + pdf.get("_id"));
        
        // ================ Get back out
        
        GridFSDBFile gridFile = pdfs.findOne(new BasicDBObject("filename", "Concurrency.pdf"));
        
        FileOutputStream outputStream = new FileOutputStream("Concurrency_copy.pdf");        
        gridFile.writeTo(outputStream);
    }
}

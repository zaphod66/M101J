package com.tengen.week1;

import java.io.*;
import java.net.*;
import com.mongodb.*;

import spark.*;
import spark.Response;
import freemarker.template.*;

public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
        
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        
        DB database = client.getDB("test");
        final DBCollection collection = database.getCollection("hello");

        Spark.get(new Route("/") {

            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();

                DBObject document = collection.findOne();
                
                try {
                    Template helloTemplate = config.getTemplate("hello.ftl");
                    
                    helloTemplate.process(document, writer);
                } catch (Exception e) {
                    halt(500);  // telling spark to send error 500
                    e.printStackTrace();
                }
                
                return writer;
            }
            
        });
        
        Spark.get(new Route("/echo/:thing") {

            @Override
            public Object handle(Request request, Response response) {
                return request.params(":thing");
            }
            
        });
    }
}

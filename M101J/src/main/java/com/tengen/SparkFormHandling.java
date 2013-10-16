package com.tengen;

import java.io.*;
import java.net.*;
import java.util.*;

import spark.*;

import freemarker.template.*;

public class SparkFormHandling {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                try {
                    Map<String,Object> fruitMap = new HashMap<String,Object>();
                    fruitMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
                    
                    Template fruitPickerTemplate = config.getTemplate("fruitPicker.ftl");
                    
                    StringWriter writer = new StringWriter();
                    fruitPickerTemplate.process(fruitMap,  writer);
                    
                    return writer;
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                    return null;
                }
            }
        });
        
        Spark.post(new Route("/favorite_fuite") {
            @Override
            public Object handle(final Request request, final Response response) {
                final String fruit = request.queryParams("fruit");
                
                if (fruit == null) {
                    return "Why don't you pick one?";
                } else {
                    return "Your favorite fruit is " + fruit + ".";
                }
            }
        });
    }
}

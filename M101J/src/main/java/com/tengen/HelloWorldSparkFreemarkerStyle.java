package com.tengen;

import java.io.*;
import java.util.*;

import freemarker.template.*;
import spark.*;

public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
        
        Spark.get(new Route("/") {

            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                
                Map<String,Object> helloMap = new HashMap<String,Object>();
                helloMap.put("name", "Freemarker");
                
                try {
                    Template helloTemplate = config.getTemplate("hello.ftl");
                    
                    helloTemplate.process(helloMap, writer);
                    
                //  System.out.println(writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                
                return writer;
            }
            
        });
    }

}

package com.tengen;

import java.io.*;
import java.util.*;

import freemarker.template.*;

public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
        
        try {
            Template helloTemplate = config.getTemplate("hello.ftl");
            
            Map<String,Object> helloMap = new HashMap<String,Object>();
            helloMap.put("name", "Freemarker");
            
            StringWriter writer = new StringWriter();
            
            helloTemplate.process(helloMap, writer);
            
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

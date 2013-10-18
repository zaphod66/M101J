package com.tengen.week1;

import spark.*;

public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {

            @Override
            public Object handle(final Request request, final Response response) {
                return "<H1>Hello World from Spark</H1>";
            }
            
        });
    }
}

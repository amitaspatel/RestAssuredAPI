package utilities;

import reporting.TestLogger;


public class Path {

    public static String URL = "https://reqres.in";

    public String getEndPoint(){
        TestLogger.log("Base URI : " + URL);
        return URL;
    }

    public static String getEndPoint(String resource){
        TestLogger.log("URI End Point : " + URL + resource);
        return URL + resource;
    }

}


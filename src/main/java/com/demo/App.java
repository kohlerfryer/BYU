package com.demo;
import com.google.gson.Gson;

/**
* Hello world!
*
*/

public class App{
   public static void main( String[] args ){
       Gson gson = new Gson();
       gson.toJson(1);            // ==> 1
       ;       // ==> "abcd"
       gson.toJson(new Long(10)); // ==> 10
       int[] values = { 1 };
       System.out.println(gson.toJson("abcd"));       // ==> [1]

   }
}

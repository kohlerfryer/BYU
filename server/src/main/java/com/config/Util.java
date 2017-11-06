package com.familymap;

import java.util.Random;
import java.util.ArrayList;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;
import java.util.Random;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.util.concurrent.ThreadLocalRandom;

public class Util{

    public static String generateRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String getHash(String string){
        byte[] theDigest = null;
        try{
            byte[] bytesOfMessage = string.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            theDigest = messageDigest.digest(bytesOfMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(Hex.encodeHex(theDigest));
    }

    public static int getRandomNumber(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max-1);
    }

    public static String arrayListToString(ArrayList list){
        String listString = "(";
        for (int i = 0; i < list.size(); i++) {
            listString += i > 0 ? (", '" + list.get(i) + "'") : ("'" + list.get(i) + "'");
        }
        listString += ")";
        return listString;
    }


    // public static void printGsonObject(){
    //    Gson gson = new Gson();
    //    System.out.println(gson.toJson("abcd")); 
    // }

}
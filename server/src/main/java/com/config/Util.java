package com.familymap;

import java.util.Random;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

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

    // public static String inputStreamToString(){

    // }

    

}
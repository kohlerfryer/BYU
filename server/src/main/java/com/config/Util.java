package com.familymap;

import java.util.Random;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;
import java.util.Random;

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

    public static String generateFirstName(String gender){
        String[] maleNames = {
            "Kevin",
            "Zack",
            "Ken",
            "Benjamin",
            "Alonzo",
            "Sam",
            "Stanley",
            "Kermit",
            "Augustine",
            "Silas",
            "Sol",
            "Franklyn",
            "Clement",
            "Ezra",
            "Lee",
            "Hal",
            "Bruce",
            "Clifford",
            "Wilbert",
            "Lonny",
            "Marco",
            "Vaughn",
            "Brandon"
        };

        String[] femaleNames = {
            "Jolynn",
            "Maryland",
            "Marjorie",
            "Arletta",
            "Sanda",
            "Kyong",
            "Rosette",
            "Tera",
            "Spencer",
            "Jannette",
            "Gladys",
            "Nichole",
            "Terisa",
            "Tenesha",
            "Rebecca",
            "Alpha",
            "Elena",
            "Marsha",
            "Kelle",
            "Deedee",
            "Celsa",
            "Marget",
            "Anette",
        };

        if(gender == "F"){
            int index = new Random().nextInt(femaleNames.length);
            return femaleNames[index];
        }else{
            int index = new Random().nextInt(maleNames.length);
            return maleNames[index];
        }

}

    

}
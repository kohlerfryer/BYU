package com.familymap;

/**
* represents a single Authentication tuple
*/
public class Authentication{

    /** tuples unique identifier in relation */
    private String id;

    /** unique token used for establishing logging sessions */
    private String token;

    /** unique userId*/
    private String userId;

    /** used to expire timestamps */
    private String timestamp;

    public Authentication(String id, String token, String userId, String timestamp){
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getId(){
        return this.id;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getToken(){
        return this.token;
    }
    public String getTimestamp(){
        return this.timestamp;
    }

    public void setToken(String token){
        this.token = token;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
    
}
package fryer.kohler.familymapapp;

import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.Assert.*;

import java.net.URL;
import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.Person;
import familymapapp.Proxy.GetRequest;
import familymapapp.Proxy.PostRequest;
import familymapapp.Proxy.Proxy;
import familymapapp.Request.LoginRequestBody;
import familymapapp.Response.EventsResponse;
import familymapapp.Util.Settings;
import familymapapp.Util.Util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class ProxyUnitTest {

    @Test
    public void testBasicSuccessfullGetRequest(){
        Consumer<String> success = (data) -> {
            assertNotNull(Util.getValueFromJson(data, "personId"));
        };
        Consumer<String> failure = (data) -> {

        };
        GetRequest getRequest = new GetRequest("application/json", success, failure, "XZJ4ID6DDQQFGXRKV6");
        try{
            URL url = new URL("http://10.0.2.2:8000/person/BettyWhite");
            getRequest.execute(url);
        }catch(Exception e){

        }
    }

    @Test
    public void testBasicFailingGetRequest(){
        Consumer<String> success = (data) -> {
            assertTrue(false);
        };
        Consumer<String> failure = (data) -> {
            assertTrue(true);
        };
        GetRequest getRequest = new GetRequest("application/json", success, failure, "XZJ4ID6DDQQFGXRKV6");
        try{
            URL url = new URL("fail:(");
            getRequest.execute(url);
        }catch(Exception e){

        }
    }

    @Test
    public void testBasicSuccessfullPostRequest(){
        Consumer<String> success = (data) -> {
            assertNotNull(Util.getValueFromJson(data, "personId"));
        };
        Consumer<String> failure = (data) -> {

        };
        LoginRequestBody requestBody = new LoginRequestBody("patrick", "sheila");
        PostRequest loginRequest = new PostRequest(requestBody.toString(), "application/json", success, failure, null);
        try{
            URL url = new URL("http://10.0.2.2:8000/login");
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }

    @Test
    public void testBasicFailingPostRequest(){
        Consumer<String> success = (data) -> {
            assertTrue(false);
        };
        Consumer<String> failure = (data) -> {
            assertTrue(true);
        };
        String fakebody = ";faskfjas;ldfjas;ldfj";
        PostRequest loginRequest = new PostRequest(fakebody, "application/json", success, failure, null);
        try{
            URL url = new URL("http://10.0.2.2:8000/login");
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }

    @Test
    public void testNotReadySync(){
        Proxy.authenticationToken = null;
        Proxy.rootPersonId = null;
        Consumer<String> success = (data) -> {
        };
        Consumer<String> failure = (data) -> {
        };
        assertFalse(Proxy.syncData(success, failure));
    }

    @Test
    public void testValidSync(){
        DataTree.clear();
        String rootPersonId = "SheilaParker";
        Proxy.authenticationToken = "XZJ4ID6DDQQFGXRKV6";
        Proxy.rootPersonId = rootPersonId;
        Consumer<String> success = (data) -> {
            assertEquals(rootPersonId, DataTree.getRootPerson().getId());
            assertTrue(DataTree.getEvents().size() > 0);
            assertTrue(DataTree.getFilteredEvents().size() > 0);
            assertTrue(DataTree.getEvents().size() > 0);
            assertTrue(DataTree.getPersons().size() > 0);
        };
        Consumer<String> failure = (data) -> {
        };
        Proxy.syncData(success, failure);
    }

}

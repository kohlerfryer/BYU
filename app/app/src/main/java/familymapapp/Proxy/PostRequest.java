package familymapapp.Proxy;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import java.util.function.Consumer;

import familymapapp.Util.Util;

/**
 * Created by kittykatt on 11/12/17.
 */

public class PostRequest extends AsyncTask<URL, Integer, HTTPResponse> {

    private Consumer<String> successCallback;
    private Consumer<String> failureCallback;
    private String postData;
    private String authenticationToken;
    private final String requestMethod = "POST";
    private String contentType;

    public PostRequest(String postData, String contentType, Consumer<String> successCallback, Consumer<String> failureCallback, String authenticationToken) {
        this.postData = postData;
        this.contentType = contentType;
        this.successCallback = successCallback;
        this.failureCallback = failureCallback;
        this.authenticationToken = authenticationToken;
    }

    @Override
    protected HTTPResponse doInBackground(URL... urls) {
        String response = "";
        boolean success = false;
        try {
            URL url = urls[0];
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(12000);
            urlConnection.setReadTimeout(12000);
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty("Content-Type", this.contentType);
            urlConnection.setRequestMethod(this.requestMethod);
            urlConnection.setRequestProperty("Authorization", this.authenticationToken);

            if (this.postData != null) {
                urlConnection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData);
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();
            InputStream inputStream;
            if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_ACCEPTED) {
                success = true;
                inputStream = urlConnection.getInputStream();
            }
            else {

                inputStream = urlConnection.getErrorStream();
            }

            response = Util.convertInputStreamToString(inputStream);

        } catch (Exception e) {
            Pair<String, String> errorKeyValue = new Pair("message", e.getLocalizedMessage());
            response = Util.createJsonString(errorKeyValue);
        }
        return new HTTPResponse(success, response);
    }

    protected void onPostExecute(HTTPResponse result) {
        if(result.wasSuccessfull()) successCallback.accept(result.getResponse());
        else failureCallback.accept(result.getResponse());

    }



}


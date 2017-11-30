package familymapapp.HTTP;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import java.util.function.Consumer;

import familymapapp.UTIL.Util;

/**
 * Created by kittykatt on 11/12/17.
 */

public class GetRequest extends AsyncTask<URL, Integer, HTTPResponse> {

    private Consumer<String> successCallback;
    private Consumer<String> failureCallback;
    private String authenticationToken;
    private final String requestMethod = "GET";
    private String contentType;

    public GetRequest(String contentType, Consumer<String> successCallback, Consumer<String> failureCallback, String authenticationToken) {

        this.contentType = contentType;
        this.successCallback = successCallback;
        this.failureCallback = failureCallback;
        this.authenticationToken = authenticationToken;
    }

    @Override
    protected HTTPResponse doInBackground(URL... urls) {
        String response = "";
        boolean success = false;
        Log.d("network", "here -9");
        try {
            URL url = urls[0];
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", this.contentType);
            urlConnection.setRequestMethod(this.requestMethod);
            urlConnection.setRequestProperty("Authorization", this.authenticationToken);
            Log.d("network", "here -1");
            int statusCode = urlConnection.getResponseCode();

            InputStream inputStream;
            if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_ACCEPTED) {
                success = true;
                inputStream = urlConnection.getInputStream();
                Log.d("network", "here1");
            }
            else {
                Log.d("network", "here");
                inputStream = urlConnection.getErrorStream();
            }
            Log.d("network", "here2");
            response = Util.convertInputStreamToString(inputStream);
            Log.d("network", response);

        } catch (Exception e) {
            Log.d("network", e.getLocalizedMessage());
            Pair<String, String> errorKeyValue = new Pair("message", e.getLocalizedMessage());
            response = Util.createJsonString(errorKeyValue);
        }
        return new HTTPResponse(success, response);
    }

    protected void onPostExecute(HTTPResponse result) {
        Log.d("network", result.getResponse());
        if(result.wasSuccessfull()) successCallback.accept(result.getResponse());
        else failureCallback.accept(result.getResponse());

    }



}


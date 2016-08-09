package com.julyobserver.codepath.rottentomatoes;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by SSubra27 on 7/15/16.
 */
public class TomatoFetcher
{
    public static final String TAG = "TomatoFetcher";
    public static final String API_KEY = "Your API KEY";
    private URL url;
    public TomatoFetcher()
    {

    }
    public byte[] getUrlBytes(String urlSpec) throws IOException
    {
        url = new URL(urlSpec);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if(connection.getResponseCode()!= HttpURLConnection.HTTP_OK)
            {
                throw new IOException(connection.getResponseMessage() + " :with"+ urlSpec);

            }
            int bytesRead = 0;
            byte[] buffer = new byte[2048];
            while((bytesRead = in.read(buffer))>0)
            {
                out.write(buffer,0,bytesRead);
            }
            out.close();
            return  out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException

    {
        byte [] b = getUrlBytes(urlSpec);
        String s = new String (b);

        return s;

        //return new String(getUrlBytes(urlSpec));
    }

    public ArrayList<Movie> fetchItems()
    {
        ArrayList<Movie> items = new ArrayList<>();
        try {
            String url = Uri.parse("https://api.themoviedb.org/3/movie/popular?").buildUpon().
                    appendQueryParameter("method", "get").appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json").appendQueryParameter("nojsoncallback", "1")
                    .build().toString();

            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            parseItems(items,jsonObject);
        }catch (JSONException je)
        {
            Log.e(TAG, "Failed to parse JSON" , je);
        } catch (IOException ioe)
        {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    public void parseItems(ArrayList<Movie> items, JSONObject jsonObject) throws IOException, JSONException
    {

        JSONArray photoJsonObject = jsonObject.getJSONArray("results");
        for(int i=0; i<photoJsonObject.length(); i++)
        {
            JSONObject photoJsonObj = photoJsonObject.getJSONObject(i);
            Movie movie = new Movie();

            movie.setPosterUrl(photoJsonObj.getString("poster_path"));
            movie.setOverview(photoJsonObj.getString("overview"));
            movie.setTitle(photoJsonObj.getString("original_title"));
            movie.setVideoPath(photoJsonObj.getInt("id"));
            movie.setBackgroundPath(photoJsonObj.getString("backdrop_path"));
            Log.i(TAG,""+movie.getVideoPath());
            items.add(movie);
        }

    }




}

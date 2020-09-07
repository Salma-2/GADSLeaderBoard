package com.example.gadsleaderboard;

import android.text.TextUtils;
import android.util.Log;

import com.example.gadsleaderboard.ui.main.LearningFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static final String LOG_TAG = LearningFragment.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {}


    private static URL createUrl (String stringUrl)
    {
        URL url = null;
        if (stringUrl == null)
        {
            return null;
        }
        try {
            url= new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;

    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Learner> extractLearners(String learnerJSON) {

        if(TextUtils.isEmpty(learnerJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding learner to
        ArrayList<Learner> learners = new ArrayList<>();
        try {

            //Extract features
            JSONArray learnersArray = new JSONArray( learnerJSON );
            for(int i=0;i<learnersArray.length();i++)
            {
                JSONObject currentLearner = learnersArray.getJSONObject(i);
                String name = currentLearner.getString("name");
                int hours = currentLearner.getInt( "hours" );
                String country = currentLearner.getString("country");
                String info = hours + " learning hours, " + country+ ".";
                Learner learner = new Learner( name, info );
                learners.add( learner );
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the learner JSON results", e);
        }

        return learners;
    }


    public static ArrayList<Learner> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<Learner> learners = extractLearners(jsonResponse);

        // Return the {@link Event}
        return learners;
    }


    //connect be url (background)
    // et2kdi eno el connection tmam
    // response 3la shkl string
    //json parsing --> 3shan a5leha json






}
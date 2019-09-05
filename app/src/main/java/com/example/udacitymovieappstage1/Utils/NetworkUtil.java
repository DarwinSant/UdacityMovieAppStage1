package com.example.udacitymovieappstage1.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    public static URL buildUrl(boolean sortPopular) {

        Uri builtUri = Uri.parse(Api_information.BASE_URL).buildUpon()
                .appendPath(sortPopular ? Api_information.POPULAR_MOVIES_URL:Api_information.TOP_MOVIES_URL)
                .appendQueryParameter(Api_information.API_PARAM, Api_information.API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    // builds full poster URL
    public static URL buildPosterUrl(String posterPath) {
        Uri builtUri = Uri.parse(Api_information.IMAGE_URL).buildUpon()
                .appendEncodedPath(posterPath)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}


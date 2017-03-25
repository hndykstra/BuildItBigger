package com.udacity.gradle.builditbigger.endpoint;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by hans.dykstra on 3/13/2017.
 */

public class JokeEndpointTask extends AsyncTask<Context, Void, String> {
    private MyApi apiEndpoint;

    @Override
    protected String doInBackground(Context... params) {
        if (apiEndpoint == null) {
            Log.d("JokeEndpointTask", "build endpoint");
            String urlEndpoint = "http://10.0.2.2:8080/_ah/api";
            apiEndpoint = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                .setRootUrl(urlEndpoint)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                })
                .build();
        }

        Context context = params[0];

        try {
            return apiEndpoint.getJoke().execute().getData();
        } catch (IOException e) {
            return "";
        }
    }
}

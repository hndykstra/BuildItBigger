package com.udacity.gradle.builditbigger.backend;

import android.content.Context;
import android.os.AsyncTask;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.endpoint.JokeEndpointTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Assert;

/**
 * Created by hans.dykstra on 3/13/2017.
 */

@RunWith(AndroidJUnit4.class)
public class BackendTest {
    @Test
    public void testAsynchTask() {
        AsyncTask<Context, Void, String> jokeTask = new JokeEndpointTask() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Assert.assertNotNull("Got null string from task", s);
                Assert.assertNotEquals("Got empty string from task", s, "");
                Log.d("BackendTest", s);
            }
        };
        jokeTask.execute(InstrumentationRegistry.getTargetContext());

        try {
            synchronized (jokeTask)
            {
                jokeTask.wait(10000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

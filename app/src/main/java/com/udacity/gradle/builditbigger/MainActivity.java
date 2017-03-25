package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.example.jokeactivity.JokeActivity;
import com.udacity.gradle.builditbigger.endpoint.JokeEndpointTask;
import com.udacity.gradle.jokelib.JokeTeller;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View viw) {
        final JokeTeller joker = new JokeTeller();
        /*
        Intent jokeIntent = new Intent(this, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_EXTRA, joker.tellAJoke());
        startActivity(jokeIntent);
        */

        AsyncTask<Context, Void, String> jokeTask = new JokeEndpointTask() {
            @Override
            protected void onPostExecute(String s) {
                Intent jokeIntent = new Intent(MainActivity.this, JokeActivity.class);
                jokeIntent.putExtra(JokeActivity.JOKE_EXTRA, joker.tellAJoke());
                startActivity(jokeIntent);
            }
        };

        jokeTask.execute(this);
    }


}

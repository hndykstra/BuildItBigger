package com.udacity.gradle.jokelib;

import java.io.IOException;
import java.util.Random;

public class JokeTeller {
    java.util.Properties props = new java.util.Properties();

    public JokeTeller() {
        try {
            // TODO: make this locale sensitive
            props.load(this.getClass().getResourceAsStream("/jokes.properties"));
        } catch (IOException e) {
            props = null;
        }
    }
    public String tellAJoke() {
        if (props == null) {
            return "No jokes available";
        } else {
            int jokeCount = Integer.parseInt(props.getProperty("jokeCount").trim());
            return props.getProperty(String.valueOf(new Random().nextInt(jokeCount)+1));
        }
    }
}

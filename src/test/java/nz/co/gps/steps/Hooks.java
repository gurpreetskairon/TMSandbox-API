package nz.co.gps.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;
import java.util.Properties;

public class Hooks {
    public static Properties globalProperties;
    public static String env;
    public static String baseURL;
    public static String endpoint;

    @Before
    public void setup() throws IOException {
        // read the global properties file
        globalProperties = new Properties();
        globalProperties.load(Hooks.class.getClassLoader().getResourceAsStream("global.properties"));

        // get the env from the value passed or the one provided in the pom file
        // if not set via system env variable, then get the env variable from the
        // global properties file
        env = setEnvironmentProperty("env.name");
        baseURL = setEnvironmentProperty(env + ".url");

    }

    @After
    public void teardown(){

    }

    public String setEnvironmentProperty(String propertyName) {
        if (System.getenv().containsKey(propertyName)) {
            return System.getenv(propertyName);
        } else if (System.getenv().containsKey(propertyName.toUpperCase())) {   //some instances of windows are uppercasing runtarget
            return System.getenv(propertyName.toUpperCase());
        } else {
            return globalProperties.getProperty(propertyName);
        }
    }

    public Boolean setBooleanEnvironmentProperty(String propertyName) {
        if (System.getenv().containsKey(propertyName))
            return Boolean.valueOf(System.getenv(propertyName));
        else
            return Boolean.valueOf(globalProperties.getProperty(propertyName));
    }
}

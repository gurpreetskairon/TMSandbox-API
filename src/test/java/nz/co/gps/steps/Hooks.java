package nz.co.gps.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.IOException;
import java.util.Properties;


public class Hooks {
    public static Properties globalProperties;
    public static String env;
    public static String baseURL;

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

    public String setEnvironmentProperty(String propertyName) {
        if (System.getenv().containsKey(propertyName)) {
            return System.getenv(propertyName);
        } else if (System.getenv().containsKey(propertyName.toUpperCase())) {   //in case property name is in uppercase
            return System.getenv(propertyName.toUpperCase());
        } else {
            return globalProperties.getProperty(propertyName);
        }
    }
}

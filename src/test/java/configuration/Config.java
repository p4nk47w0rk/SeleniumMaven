package configuration;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static String configfile = "myconfig.properties";
    private static final Properties properties = loadProperties();


    public static String getBaseUrl(){
        String baseurl = (String) properties.get("baseUrl");
        throwExceptionWhenNull("baseUrl", baseurl);
        return baseurl;
    }

    public static String getBrowserName(String browserName){
        String browser = (String) properties.get(browserName);
        throwExceptionWhenNull(browserName, browser);
        return browser;
    }

    public static String getBrowserDriver(String browserdriver){
        String browserDriver = (String) properties.get(browserdriver);
        throwExceptionWhenNull(browserDriver, browserDriver);
        return browserDriver;
    }



    /**
     * Throws a RuntimeException if the given parameter is null.
     *
     * @param  property  the name of the property being checked
     * @param  Parameter the value being checked for null
     */
    private static void throwExceptionWhenNull(String property, String Parameter) {
        if(Parameter == null){
            throw new RuntimeException("Parameter" + property + " wurde nicht in der konfigurationsdatei gefunden.");
        }

    }


    /**
     * Loads and returns a Properties object from a configuration file.
     *
     * @return          The loaded Properties object.
     */
    private static Properties loadProperties() {
        try{
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            Properties props = new Properties();

            InputStream is = loader.getSystemResourceAsStream(configfile);

            props.load(is);

            return props;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

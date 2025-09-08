package demoblaze.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc file config.properties", e);
        }
    }
    public static String get(String key) {
        return properties.getProperty(key);
    }
}


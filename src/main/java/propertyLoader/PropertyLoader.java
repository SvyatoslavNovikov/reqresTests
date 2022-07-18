/**
 * Читает значение ключей в файле config.properties
 */
package propertyLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    public static String getProperty (String propertyName) {
        return getProperty(propertyName, "src/main/resources/config.properties");
    }

    public static String getProperty (String propertyName, String fileUrl) {

        FileInputStream fis;
        Properties property = new Properties();
        String propertyFromFile = null;

        try {
            fis = new FileInputStream(fileUrl);
            property.load(fis);
            propertyFromFile = property.getProperty(propertyName);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        return propertyFromFile;
    }
}

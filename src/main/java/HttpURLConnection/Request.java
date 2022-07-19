package HttpURLConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Request {

    final URL url;
    private Integer connectionTimeout;
    private Integer readTimeout;
    private List<Property> properties;

    public Request(String url) throws MalformedURLException {
        this.url = new URL(url);
        this.properties = new ArrayList<>();
    }

    /**
     * Сохраняем параметры
     */
    public Request setProperties(String key, String value) {
        properties.add(new Property(key, value));
        return this;
    }

    /**
     * Для отправки запроса, что GET, что POST, необходимо создать объект URL и открыть на его основе соединение
     */
    private HttpURLConnection getConnection() throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    /**
     * Все сохраненные параметры добавляются в запрос
     */
    private HttpURLConnection addParams(HttpURLConnection conn, String method) throws ProtocolException {
        conn.setRequestMethod(method);

        // Добавляем таймауты если они есть
        if (connectionTimeout != null) {
            conn.setConnectTimeout(connectionTimeout);
        }

        if (readTimeout != null) {
            conn.setReadTimeout(readTimeout);
        }

        // Добавлям все параметры из массива
        for(Property prop: properties) {conn.setRequestProperty(prop.key, prop.value);
        }

        return conn;
    }

    class Property {
        String key;
        String value;

        public Property (String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}

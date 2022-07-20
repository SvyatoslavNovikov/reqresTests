package HttpURLConnection;

import java.util.ArrayList;
import java.util.List;

public class RequestSpec {

    String url;
    String path;
    Integer connectionTimeout;
    Integer readTimeout;
    List<Property> properties;

    public RequestSpec() {
        properties = new ArrayList<>();
    }

    /**
     * Задаем url для запроса
     * @param url
     * @return
     */
    public RequestSpec setBaseUrl (String url) {
        this.url = url;
        return this;
    }

    /**
     * Задаем path для запроса
     * @param path
     * @return
     */
    public RequestSpec setBasePath (String path) {
        this.path = path;
        return this;
    }

    /**
     * Задаем параметры
     */
    public RequestSpec setProperties (String key, String value) {
        this.properties.add(new Property(key, value));
        return this;
    }

    class Property {
        String key;
        String value;

        public Property (String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Задаем значение таймаута для запроса
     */
    public RequestSpec setConnectionTimeout(int timeout) {
        this.connectionTimeout = timeout;
        return this;
    }

    /**
     * Задаем значение таймаута для ответа
     */
    public RequestSpec setReadTimeout(int timeout) {
        this.readTimeout = timeout;
        return this;
    }

}

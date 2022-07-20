package HttpURLConnection;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {

    private RequestSpec spec;
    private int responseCode;
    private String responseContent;

    public Request spec(RequestSpec spec) {
        this.spec = spec;
        return this;
    }

    public Request get(String path) throws IOException {
        final HttpURLConnection connection = (HttpURLConnection) makeUrl(path).openConnection();
        connection.setRequestMethod("GET");

        setProperties(connection);
        responseCode = connection.getResponseCode();
        saveResponseContent(connection);

        return this;
    }

    public Request get() throws IOException {
        final HttpURLConnection connection = (HttpURLConnection) makeUrl().openConnection();
        connection.setRequestMethod("GET");

        setProperties(connection);
        responseCode = connection.getResponseCode();
        saveResponseContent(connection);

        return this;
    }

    public <T> T extractBodyAs(Class<T> classOfT) {
        Gson gson = new Gson();
        Object object = gson.fromJson(responseContent, classOfT);
        return Primitives.wrap(classOfT).cast(object);
    }

    /**
     * Два приватных метода, каждый формирует URL
     * @param path
     * @return
     * @throws MalformedURLException
     */

    private URL makeUrl(String path) throws MalformedURLException {
        String url = null;

        if (spec.url != null) { url = spec.url.concat(path);}
        if (url == null) { url = path; }

        return new URL(url);
    }

    private URL makeUrl() throws MalformedURLException {
        String url = null;

        if (spec.url != null) { url = spec.url; }
        if (spec.path != null) { url = url.concat(spec.path); }

        return new URL(url);
    }

    /**
     * Приватный метод, добавляет в соединение настройки из спецификации
     * @param connection
     */
    private void setProperties(HttpURLConnection connection) {
        for (RequestSpec.Property prop: spec.properties) {
            connection.setRequestProperty(prop.key, prop.value);
        }

        if (spec.connectionTimeout != null) {
            connection.setConnectTimeout(spec.connectionTimeout);
        }

        if (spec.readTimeout != null) {
            connection.setReadTimeout(spec.readTimeout);
        }
    }

    /**
     * Приватный метод, сохраняет ответ респонса в строку.
     * @param connection
     */
    private void saveResponseContent(HttpURLConnection connection) {
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            this.responseContent = content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            this.responseContent = "";
        }
    }
}

package HttpURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    private URL url;
    private HttpURLConnection connection;
    private int statusCode;
    private String body;
    private Class cls;

    /**
     * Первый метод при отправке запроса, сохраняется параметр url
     * @param url
     * @return
     * @throws IOException
     */
    public Request given(String url) throws IOException {
        this.url = new URL(url);
        connection = (HttpURLConnection) this.url.openConnection();

        return this;
    }

    /**
     * Запрос типа GET
     * @return
     * @throws IOException
     */
    public Request get() throws IOException {
        connection.setRequestMethod("GET");
        this.statusCode = connection.getResponseCode();
        return this;
    }

    /**
     * сохраняем значение в параметр body
     * @return
     */
    public Request extractBody() {
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            body = content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            body = "";
        }
        return this;
    }

    /**
     * Дессериализация body с помощью библиотеки gson
     * @param cls
     * @return
     */
    public Request as(Class cls) {
        this.cls = (Class) new Gson().fromJson(body, cls);
        return this;
    }

    public void method() {
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setConnectTimeout(CONNECTION_TIMEOUT);
//        con.setReadTimeout(CONNECTION_TIMEOUT);
    }

}

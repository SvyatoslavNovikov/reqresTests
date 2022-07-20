package reqresClasses;

public class Support {

    private String url;
    private String text;

    public Support setUrl(String url) {
        this.url = url;
        return this;
    }

    public Support setText(String text) {
        this.text = text;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

}

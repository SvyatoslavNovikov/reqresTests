package RestAssured.entities;

public class User {

    private UserData data;
    private Support support;

    public User setData(UserData data) {
        this.data = data;
        return this;
    }

    public User setData(int id, String first_name, String last_name, String email, String avatar) {
        UserData data = new UserData()
                .setId(id)
                .setFirstName(first_name)
                .setLastName(last_name)
                .setEmail(email)
                .setAvatar(avatar);
        this.data = data;
        return this;
    }

    public User setSupport(Support support) {
        this.support = support;
        return this;
    }

    public User setSupport(String url, String text) {
        Support support = new Support()
                .setUrl(url)
                .setText(text);
        this.support = support;
        return this;
    }

    public UserData getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

}

package RestAssured.entities;

public class UserData {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public UserData setId(int id) {
        this.id = id;
        return this;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData setFirstName(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public UserData setLastName(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public UserData setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }
}

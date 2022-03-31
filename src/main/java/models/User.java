package models;

public class User {
    private int user_id;
    private String first_name;
    private String last_name;
    private String gender;
    private String password;
    private int status;

    public User() {}

    // used for POST requests
    public User(String first_name, String last_name, String gender, String password, int status) {
        setFirst_name(first_name);
        setLast_name(last_name);
        setGender(gender);
        setPassword(password);
        setStatus(status);
    }

// used for PUT requests
    public User(int user_id, String first_name, String last_name, String gender, String password, int status) {
        setUser_id(user_id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setGender(gender);
        setPassword(password);
        setStatus(status);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
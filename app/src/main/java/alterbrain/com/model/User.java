package alterbrain.com.model;

public class User {
    private String name;
    private String email;
    private String password;
    private String apellido1;
    private String apellido2;

    public User() {

    }

    public User(String name, String email, String password, String apellido1, String apellido2) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
}

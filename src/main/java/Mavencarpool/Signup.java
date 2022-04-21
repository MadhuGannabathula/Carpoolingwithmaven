package Mavencarpool;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Signup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String Username;
    private String Email;
    private String password;

    @Override
    public String toString() {
        return "Signup{" +
                "ID=" + ID +
                ", Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }

    public Signup() {
    }

    public Signup(String username, String email, String password) {
        Username = username;
        Email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

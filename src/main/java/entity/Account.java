package entity;

import java.util.Date;

public class Account {
    private int id;
    private String username;
    private String password;
    private String role;
    private Date createdAt;

    public Account(String username,String password, String role, Date createAt) {
        this.createdAt = createAt;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public Account(String username, String password){
        this.password = password;
        this.username = username;
        this.role = "CUSTOMER";
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(Date createAt) {
        this.createdAt = createAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

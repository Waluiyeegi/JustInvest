package org.example;

public class User {
    Role role;
    String username;
    public User(Role role, String username){
        this.role = role;
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public Role getRole(){
        return role;
    }


}

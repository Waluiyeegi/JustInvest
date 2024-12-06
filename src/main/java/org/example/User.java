package org.example;

/***
 * User consists of a role of type Role and a username that is just a string
 */
public class User {
    Role role;
    String username;
    public User(Role role, String username){
        this.role = role;
        this.username = username;
    }

    /***
     *
     * @return String
     */
    public String getUsername(){
        return username;
    }

    /***
     *
     * @return String
     */
    public Role getRole(){
        return role;
    }


}

package org.example;

/***
 * Has the same permissions as the employee but has time restrictions
 */
public class Teller extends Employee{
    public Teller(){
        permissions.add(Permissions.TIME_RESTRICTIONS);
    }
    @Override
    public String getNameOfRole() {
        return "Teller";
    }
}

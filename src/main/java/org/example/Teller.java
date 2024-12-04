package org.example;

public class Teller extends Employee{
    public Teller(){
        permissions.add(Permissions.TIME_RESTRICTIONS);
    }
    @Override
    public String getNameOfRole() {
        return "Teller";
    }
}

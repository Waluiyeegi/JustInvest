package org.example;

class Employee extends AbstractRole{
    public Employee(){
        permissions.add(Permissions.VIEW_BALANCE);
        permissions.add(Permissions.VIEW_PORTFOLIO);
    }
    @Override
    public String getNameOfRole() {
        return "Employee";
    }
}

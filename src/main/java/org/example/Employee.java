package org.example;

//Employee is a role that has these permissions
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

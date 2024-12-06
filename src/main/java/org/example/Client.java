package org.example;

//client is a role that has these permissions
class Client extends AbstractRole{

    public Client(){
        permissions.add(Permissions.VIEW_BALANCE);
        permissions.add(Permissions.VIEW_PORTFOLIO);
        permissions.add(Permissions.VIEW_ADVISOR_CONTACT);
    }
    @Override
    public String getNameOfRole() {
        return "Client";
    }
}

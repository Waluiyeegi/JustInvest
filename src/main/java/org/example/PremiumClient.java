package org.example;


class PremiumClient extends Client {
    public PremiumClient(){
        permissions.add(Permissions.MODIFY_PORTFOLIO);
        permissions.add(Permissions.VIEW_PLANNING_CONTACT);
    }
    @Override
    public String getNameOfRole() {
        return "PremiumClient";
    }
}

package org.example;

/***
 * Has all the permissions of client plus the 2 extra ones
 */
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

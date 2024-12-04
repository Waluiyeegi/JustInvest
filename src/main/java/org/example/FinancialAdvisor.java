package org.example;

public class FinancialAdvisor extends Employee{
    public FinancialAdvisor(){
        permissions.add(Permissions.MODIFY_PORTFOLIO);
        permissions.add(Permissions.VIEW_PRIVATE_CONSUMER_INSTRUMENTS);
    }
    @Override
    public String getNameOfRole() {
        return "Financial Advisor";
    }
}
package org.example;

public class FinancialPlanner extends FinancialAdvisor{
    //financial planner has the same permissions that financial advisor has
    public FinancialPlanner(){
        permissions.add(Permissions.VIEW_MONEY_MARKET_INSTRUMENTS);
    }
    @Override
    public String getNameOfRole() {
        return "Financial Planner";
    }
}

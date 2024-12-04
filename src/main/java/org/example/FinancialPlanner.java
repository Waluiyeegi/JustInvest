package org.example;

public class FinancialPlanner extends FinancialAdvisor{
    public FinancialPlanner(){
        permissions.add(Permissions.VIEW_MONEY_MARKET_INSTRUMENTS);
    }
    @Override
    public String getNameOfRole() {
        return "Financial Planner";
    }
}

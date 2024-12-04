package org.example;

public enum Permissions {
    VIEW_BALANCE("1:View Balance:Viewing Balance"),
    VIEW_PORTFOLIO("2:View Portfolio:Viewing Portfolio"),
    MODIFY_PORTFOLIO("3:Modify Portfolio:Modifying Portfolio"),
    VIEW_ADVISOR_CONTACT("4:View Advisor Contact:Viewing Advisor Contact"),
    VIEW_PLANNING_CONTACT("5:View Planning Contact:Viewing Planning Contact"),
    VIEW_PRIVATE_CONSUMER_INSTRUMENTS("6:View Private Consumer Instruments:Viewing Private Consumer Instruments"),
    VIEW_MONEY_MARKET_INSTRUMENTS("7:View Money Market Instruments:Viewing Money Market Instruments"),
    TIME_RESTRICTIONS("8:Time Restrictions");

    private final String showName;

    Permissions(String showName) {
        this.showName = showName;
    }

    public String getName() {
        return showName;
    }
}
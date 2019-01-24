package com.sumslack.web.simple.constant;

public enum Role {
    CUSTOMER_MGR("CUSTOMER_MGR", "客户经理"),
    TEAM_MGR("TEAM_MGR", "团队经理"),
    FINANCE("FINANCE", "财务"),
    HR("HR", "人事");


    private String value;
    private String display;

    Role(java.lang.String value, java.lang.String display) {
        this.value = value;
        this.display = display;
    }

    public java.lang.String getValue() {
        return value;
    }

    public java.lang.String getDisplay() {
        return display;
    }
}

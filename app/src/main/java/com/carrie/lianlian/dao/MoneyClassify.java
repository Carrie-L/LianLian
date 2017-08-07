package com.carrie.lianlian.dao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "MONEY_CLASSIFY".
 */
public class MoneyClassify {

    private String icon;
    private String name;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public MoneyClassify() {
    }

    public MoneyClassify(String name) {
        this.name = name;
    }

    public MoneyClassify(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
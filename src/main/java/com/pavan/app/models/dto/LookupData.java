package com.pavan.app.models.dto;

public class LookupData {

    private final String name;
    private final String value;

    public LookupData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LookupData{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

package com.repuestos.finnegans.entity;

public enum Status {
    NEW("NEW"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

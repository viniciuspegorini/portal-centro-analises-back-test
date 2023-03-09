package com.portal.centro.API.enums;

public enum RoleType {
    ADMIN("admin"),
    PROFESSOR("professor"),
    STUDENT("student");

    private String content;

    private RoleType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
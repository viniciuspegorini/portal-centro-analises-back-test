package com.portal.centro.API.enums;

public enum Role {
    ADMIN("admin"),
    PROFESSOR("professor"),
    STUDENT("student");

    private String content;

    private Role (String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
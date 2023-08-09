package com.portal.centro.API.enums;

public enum Type {
    PROFESSOR("professor"),
    STUDENT("student"),
    EXTERNAL("external"),
    ADMIN("admin");

    private String content;

    Type (String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

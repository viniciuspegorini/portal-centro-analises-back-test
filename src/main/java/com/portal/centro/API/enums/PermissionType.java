package com.portal.centro.API.enums;

public enum PermissionType {
    ACCESS("access"),
    DELETE("delete"),
    CREATE("create"),
    UPDATE("update");

    private String content;

    private PermissionType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
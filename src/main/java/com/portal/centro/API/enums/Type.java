package com.portal.centro.API.enums;

public enum Type {
    BASE("base");

    private String content;

    private Type (String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
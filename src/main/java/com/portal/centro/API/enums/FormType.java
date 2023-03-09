package com.portal.centro.API.enums;

public enum FormType {
    BASE("base");

    private String content;

    private FormType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
package com.portal.centro.API.enums;

public enum SolicitationStatus {
    PENDING("pending"),
    REFUSED("refused"),
    CORRECTION("correction"),
    APPROVED("approved");

    private String content;

    SolicitationStatus(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}

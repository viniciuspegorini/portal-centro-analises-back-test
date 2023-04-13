package com.portal.centro.API.enums;

public enum TransactionType {

    DEPOSIT("+"),
    WITHDRAW("-");

    private String content;

    TransactionType (String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

package com.portal.centro.API.enums;

public enum TypeUser {
    UTFPR("utfpr"),
    PATNER("patner"),
    PF_PJ("pf_pj");

    private String content;

    TypeUser (String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

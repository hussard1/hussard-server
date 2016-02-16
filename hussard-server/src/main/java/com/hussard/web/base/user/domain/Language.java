package com.hussard.web.base.user.domain;

/**
 * Created by Matthew on 2015-06-08.
 */
public enum Language {

    ko("Korean"),
    en("English");

    private String detail;

    Language(String detail) {
        this.detail = detail;
    }

    public String getLanguage() {
        return detail;
    }
}

package com.hussard.web.base.domain;

import java.io.Serializable;

/**
 * Created by Matthew on 2014-08-25.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -1549180214373932476L;

    private long id;
    private String languageCode;
    private String languageName;
    private String countryCode;
    private String countryName;
    private String code;
    private String message;
    private DefaultColumns defaultColumns = new DefaultColumns();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DefaultColumns getDefaultColumns() {
        return defaultColumns;
    }

    public void setDefaultColumns(DefaultColumns defaultColumns) {
        this.defaultColumns = defaultColumns;
    }
}

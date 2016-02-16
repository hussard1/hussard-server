package com.hussard.web.base.user.domain;

import com.hussard.web.base.type.DomainType;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matthew on 2015-06-09.
 */
@Entity
@Table(name = "token")
public class Token implements Serializable {

    private static final long serialVersionUID = 8470242758025962092L;

    @Id
    @Column(nullable = false, length = DomainType.TOKEN_KEY)
    private String series;

    @Column(nullable = false, length = DomainType.USERNAME)
    private String username;

    @Column(nullable = false, length = DomainType.TOKEN_KEY)
    private String tokenValue;

    @Column(nullable = false)
    private Date lastUsed;

    public Token() {
        lastUsed = new Date();
    }

    public Token(PersistentRememberMeToken token) {
        this.username = token.getUsername();
        this.series = token.getSeries();
        this.tokenValue = token.getTokenValue();
        this.lastUsed = token.getDate();
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}

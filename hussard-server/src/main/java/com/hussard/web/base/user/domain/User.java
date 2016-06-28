package com.hussard.web.base.user.domain;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.type.DomainType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by Matthew on 2015-06-08.
 */
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 7335493241607963381L;

    @Id
    @Column
    private long id;

    @Column(unique = true, length = DomainType.USERNAME)
    private String username;

    @Column(nullable = false, length = DomainType.PASSWORD)
    private String password;

    @Column(nullable = false, length = DomainType.USERNAME)
    private String fullname;

    @Column
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column
    private boolean active;

    @Column
    private Date created;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Group> groups;

    public User() {
        active = true;
        created = new Date();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void setEnabled(boolean active) {
        this.active = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}

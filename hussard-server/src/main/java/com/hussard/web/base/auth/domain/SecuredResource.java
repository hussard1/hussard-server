package com.hussard.web.base.auth.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by hussard on 2016. 6. 14..
 */
@Entity
@Table(name="secured_resource")
public class SecuredResource {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "resource_name")
    private String name;

    @Column(name = "resource_pattern")
    private String pattern;

    @Column(name = "resource_type")
    private String type;

    @Column(name = "sort_order")
    private int sortOrder;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}

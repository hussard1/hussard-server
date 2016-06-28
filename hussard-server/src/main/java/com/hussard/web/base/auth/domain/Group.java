package com.hussard.web.base.auth.domain;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by hussard on 2016. 6. 14..
 */

@Entity
@Table(name = "group")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "group_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}

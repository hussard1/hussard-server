package com.hussard.web.base.auth.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by hussard on 2016. 6. 14..
 */
@Entity
@Table(name = "authority")
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "authority_name")
    private String name;
}

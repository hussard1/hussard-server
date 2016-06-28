package com.hussard.web.base.auth.domain;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by hussard on 2016. 6. 14..
 */
@Entity
@Table(name="secured_resource")
@Data
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
}

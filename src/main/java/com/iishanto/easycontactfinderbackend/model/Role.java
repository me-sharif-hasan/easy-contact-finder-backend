package com.iishanto.easycontactfinderbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.Set;
@ToString
@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String roleName;

    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean isEnabled=true;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> userList;
}
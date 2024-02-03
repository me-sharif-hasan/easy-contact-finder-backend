package com.iishanto.easycontactfinderbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.NaturalId;
import org.springframework.context.annotation.Primary;

import java.util.Set;
@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @NaturalId
    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean realUser=false;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isHidden=false;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted=false;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean usedSocialLogin=false;

    private String picture=null;
    @OneToMany
    private Set <Phone> phones;
    @ManyToMany
    private Set <Role> roles;
}

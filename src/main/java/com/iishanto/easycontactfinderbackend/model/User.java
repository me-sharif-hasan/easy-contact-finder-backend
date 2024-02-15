package com.iishanto.easycontactfinderbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User implements UserDetails {
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

    private Boolean isPhotoVerified=false;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private Set <Phone> phones;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set <Role> roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private UserVerification userVerification;

//    @OneToMany
//    private List<PhoneAlias> savedContacts;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

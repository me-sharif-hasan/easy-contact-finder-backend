package com.iishanto.easycontactfinderbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;


@Data
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryCode;
    @Column(nullable = false)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User owner;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isHidden=false;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private PhoneVerification phoneVerification;
}

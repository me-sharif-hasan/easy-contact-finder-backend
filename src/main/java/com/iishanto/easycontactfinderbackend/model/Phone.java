package com.iishanto.easycontactfinderbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@ToString
@Getter
@Setter
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryCode;
    @Column(nullable = false)
    private String number;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isHidden=false;
}

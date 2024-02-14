package com.iishanto.easycontactfinderbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;


@Data
@ToString
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "user_phone_unique",columnNames = {"alias_target_id","alias_owner_id"})
})
public class PhoneAlias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String name;
    @ManyToOne
    private Phone aliasTarget;
    @ManyToOne
    private User aliasOwner;
}

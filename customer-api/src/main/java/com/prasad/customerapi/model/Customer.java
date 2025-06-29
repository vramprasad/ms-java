package com.prasad.customerapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "cust_gen", sequenceName = "seq_cust",  initialValue = 1532, allocationSize = 1)
@Entity
@Table(name = "tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_gen")
    @Column(nullable=false, updatable=false)
    private Long id;
    private String custName;
    private String custEmail;

}
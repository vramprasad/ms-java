package com.prasad.productapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "prod_gen", sequenceName = "seq_prod",  initialValue = 4701, allocationSize = 1)
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_gen")
    @Column(nullable=false, updatable=false)
    private long id;
    private String productName;
    private int productAvlQty;
    private float productPrice;
    private float discPercent;

}
package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "product_cat_id")
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private Brands brand;

    @ManyToOne
    @JoinColumn(name = "product_supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    private String productDesc;
}

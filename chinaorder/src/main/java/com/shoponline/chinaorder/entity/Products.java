package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
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

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "is_delete")
    private int isDelete;
}

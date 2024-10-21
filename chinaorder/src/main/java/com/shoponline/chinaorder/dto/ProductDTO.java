package com.shoponline.chinaorder.dto;

import com.shoponline.chinaorder.entity.Brands;
import com.shoponline.chinaorder.entity.Categories;
import com.shoponline.chinaorder.entity.Supplier;
import com.shoponline.chinaorder.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {
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

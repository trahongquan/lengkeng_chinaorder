package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "imageproduct")
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Products product;

    @Column(name = "imgurl")
    private String imgurl;

    public ImageProduct(Products product, String filePath) {
        this.product = product;
        this.imgurl = filePath;
    }
}
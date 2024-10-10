package com.shoponline.chinaorder.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "variants")
public class Variants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String variantName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes size;
}

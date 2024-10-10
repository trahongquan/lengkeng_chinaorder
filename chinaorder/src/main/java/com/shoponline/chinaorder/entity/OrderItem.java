package com.shoponline.chinaorder.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variants variant;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    private BigDecimal costPrice;
    private BigDecimal sellingPrice;
    private BigDecimal discount;
    private BigDecimal discountedPrice;
    private int quantity;
    private BigDecimal amount;
}

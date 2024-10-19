package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher Voucher;
    private BigDecimal discountedPrice;
    private int quantity;
    private BigDecimal amount;
}

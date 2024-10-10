package com.shoponline.chinaorder.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variants variant;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;

    private int qty;
}

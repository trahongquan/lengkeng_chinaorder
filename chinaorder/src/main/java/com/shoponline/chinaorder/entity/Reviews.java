package com.shoponline.chinaorder.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variants variant;

    private String name;
    private String email;
    private String review;

    @Temporal(TemporalType.DATE)
    private Date datetime;

    private int rating;
}


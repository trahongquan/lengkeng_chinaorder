package com.shoponline.chinaorder.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "brands")
public class Brands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brandname;
}

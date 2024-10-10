package com.shoponline.chinaorder.entity;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "sizes")
public class Sizes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sizeName;
}

package com.shoponline.chinaorder.entity;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "colors")
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;
    private String hexCode;
}


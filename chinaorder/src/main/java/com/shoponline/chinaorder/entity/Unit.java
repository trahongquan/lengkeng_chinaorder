package com.shoponline.chinaorder.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String unit;
}


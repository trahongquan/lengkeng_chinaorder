package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "colors")
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;
    private String abbreviations;
    @Column(name = "hex_code")
    private String hexCode;

    public Colors(String color, String abbreviations, String hexCode) {
        this.color = color;
        this.abbreviations = abbreviations;
        this.hexCode = hexCode;
    }
}


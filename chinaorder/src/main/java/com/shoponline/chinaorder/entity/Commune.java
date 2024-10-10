package com.shoponline.chinaorder.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "commune")
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String communename;
    @Column(name = "commune_Code")
    private int communeCode;
    @Column(name = "district_Code")
    private int districtCode;
    @Column(name = "province_Code")
    private int provinceCode;
    @Column(name = "is_delete")
    private int isDelete;
}

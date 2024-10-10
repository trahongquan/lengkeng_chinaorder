package com.shoponline.chinaorder.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String districtname;
    @Column(name = "district_Code")
    private int districtCode;
    @Column(name = "province_Code")
    private int provinceCode;
    @Column(name = "is_delete")
    private int isDelete;
}

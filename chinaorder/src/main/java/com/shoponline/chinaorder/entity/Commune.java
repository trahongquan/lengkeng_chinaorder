package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
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

    public Commune(String communename, int communeCode, int districtCode, int provinceCode, int isDelete) {
        this.communename = communename;
        this.communeCode = communeCode;
        this.districtCode = districtCode;
        this.provinceCode = provinceCode;
        this.isDelete = isDelete;
    }
}

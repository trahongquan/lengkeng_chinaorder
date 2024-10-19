package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
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

    public District(String districtname, int districtCode, int provinceCode, int isDelete) {
        this.districtname = districtname;
        this.districtCode = districtCode;
        this.provinceCode = provinceCode;
        this.isDelete = isDelete;
    }
}

package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String provincename;
    @Column(name = "province_Code")
    private int provinceCode;
    @Column(name = "is_delete")
    private int isDelete;

    public Province(String provincename, int provinceCode, int isDelete) {
        this.provincename = provincename;
        this.provinceCode = provinceCode;
        this.isDelete = isDelete;
    }
}


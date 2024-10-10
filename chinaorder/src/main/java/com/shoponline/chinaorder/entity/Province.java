package com.shoponline.chinaorder.entity;

import lombok.Data;

import javax.persistence.*;

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
}


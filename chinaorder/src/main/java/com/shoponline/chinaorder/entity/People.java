package com.shoponline.chinaorder.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "address_default")
    private Address addressDefault;
    @Column(name = "is_delete")
    private int isDelete;
}
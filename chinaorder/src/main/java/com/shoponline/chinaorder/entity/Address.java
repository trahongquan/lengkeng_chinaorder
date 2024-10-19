package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "commune_id")
    private Commune commune;

    private String address;
    @Column(name = "is_delete")
    private int isDelete;

    public Address(People people, Province province, District district, Commune commune, String address, int isDelete) {
        this.people = people;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.address = address;
        this.isDelete = isDelete;
    }
}

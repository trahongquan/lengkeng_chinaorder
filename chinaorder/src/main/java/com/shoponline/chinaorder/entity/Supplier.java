package com.shoponline.chinaorder.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String suppliername;
    private String address;
    private String contact;

    public Supplier(String suppliername, String address, String contact) {
        this.suppliername = suppliername;
        this.address = address;
        this.contact = contact;
    }
}

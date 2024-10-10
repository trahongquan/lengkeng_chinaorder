package com.shoponline.chinaorder.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String suppliername;
}

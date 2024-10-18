package com.shoponline.chinaorder.entity;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String statusname;
}


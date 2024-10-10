package com.shoponline.chinaorder.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String catname;
}


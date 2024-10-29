package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variants variant;

    @Column(name = "type")
    private String type;

    @Column(name = "imgname")
    private String imgname;

    @Column(name = "imgurl")
    private String imgurl;

    public Image(Variants variant, String imgurl) {
        this.variant = variant;
        this.imgurl = imgurl;
    }
}
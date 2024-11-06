package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "imagebanner")
public class ImageBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "imgurl")
    private String imgurl;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "buttonText")
    private String buttonText;

    @Column(name = "active")
    private int active;

    public ImageBanner(String type, String imgurl, String title, String subtitle, String buttonText, int active) {
        this.type = type;
        this.imgurl = imgurl;
        this.title = title;
        this.subtitle = subtitle;
        this.buttonText = buttonText;
        this.active = active;
    }
}
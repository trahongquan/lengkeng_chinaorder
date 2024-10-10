package com.shoponline.chinaorder.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "logs")
public class Logs {
    @Id
    private int id;

    private String userId;
    private String action;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}

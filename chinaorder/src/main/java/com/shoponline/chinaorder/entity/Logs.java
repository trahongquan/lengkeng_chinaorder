package com.shoponline.chinaorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
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

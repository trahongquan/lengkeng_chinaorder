package com.shoponline.chinaorder.dto;

import com.shoponline.chinaorder.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCategory {
    private Products product;
    private Variants variant;
    private int variants;
    private ImageProduct imageProduct;
}

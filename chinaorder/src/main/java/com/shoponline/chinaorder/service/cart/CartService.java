// CartService.java
package com.shoponline.chinaorder.service.cart;

import com.shoponline.chinaorder.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();

    Cart createCart(Cart cart);

    Cart findCartById(int id);

    void deleteCart(int id);
}

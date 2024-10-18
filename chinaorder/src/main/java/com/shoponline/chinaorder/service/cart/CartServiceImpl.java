// CartServiceImpl.java
package com.shoponline.chinaorder.service.cart;

import com.shoponline.chinaorder.dao.CartRepository;
import com.shoponline.chinaorder.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartById(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }
}

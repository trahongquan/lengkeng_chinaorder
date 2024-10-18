// OrdersServiceImpl.java
package com.shoponline.chinaorder.service.orders;

import com.shoponline.chinaorder.dao.OrderRepository;
import com.shoponline.chinaorder.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository ordersRepository;

    @Override
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Order createOrder(Order orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Order findOrderById(int id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrder(int id) {
        ordersRepository.deleteById(id);
    }
}

// OrdersService.java
package com.shoponline.chinaorder.service.orders;

import com.shoponline.chinaorder.entity.Order;

import java.util.List;

public interface OrdersService {
    List<Order> getAllOrders();

    Order createOrder(Order order);

    Order findOrderById(int id);

    void deleteOrder(int id);
}

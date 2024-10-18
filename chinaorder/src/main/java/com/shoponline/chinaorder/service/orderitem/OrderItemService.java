// OrderItemService.java
package com.shoponline.chinaorder.service.orderitem;

import com.shoponline.chinaorder.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();

    OrderItem createOrderItem(OrderItem orderItem);

    OrderItem findOrderItemById(int id);

    void deleteOrderItem(int id);
}

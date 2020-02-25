package com.example.myorder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myorder.api.dtos.CreateOrderDto;
import com.example.myorder.api.dtos.OrderResponseDto;
import com.example.myorder.entities.Order;
import com.example.myorder.entities.OrderItem;
import com.example.myorder.enums.OrderStatusEnum;
import com.example.myorder.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    public OrderResponseDto create(CreateOrderDto createOrderDto) {
        Order createOrder = createOrder(createOrderDto);

        Order order = orderRepository.save(createOrder);

        return new OrderResponseDto()
                .setId(order.getId())
                .setOrderStatus(order.getStatus())
                .setTotalValue(order.getTotalValue())
                .setItens(orderItemService.buildOrderItemDtos(order.getItems()));
    }

    private Order createOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        List<OrderItem> itens = orderItemService.createOrderItens(createOrderDto.getOrderItens(), order);

        return order.setStatus(OrderStatusEnum.OPEN)
                .setRestaurant(restaurantService.findById(createOrderDto.getRestaurantId()))
                .setUser(userService.findById(createOrderDto.getUserId()))
                .setItems(itens)
                .setTotalValue(calculateTotalValue(itens));
    }

    private Double calculateTotalValue(List<OrderItem> itens) {
        return itens.stream().map(orderItem -> orderItem.getProduct().getValue() * orderItem.getQuantity())
                .reduce(0d, Double::sum);
    }
}

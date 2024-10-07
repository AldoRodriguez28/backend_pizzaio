package com.pizzaio.pizza.service;

import com.pizzaio.pizza.persistence.entity.OrderEntity;
import com.pizzaio.pizza.persistence.entity.PizzaEntity;
import com.pizzaio.pizza.persistence.pojection.OrderSummary;
import com.pizzaio.pizza.persistence.repository.OrderRepository;
import com.pizzaio.pizza.service.dto.RamdomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    public final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    public OrderEntity getById(int idOrder){
        return this.orderRepository.findById(idOrder).orElse(null);
    }

    public List<OrderEntity> getAfterTodayOrder(){
        LocalDateTime today  = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }
    public List<OrderEntity> getBeforeTodayOrder(){
        LocalDateTime today  = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateBefore(today);
    }
    public OrderEntity save(OrderEntity order){
        return this.orderRepository.save(order);
    }
    public boolean isOrder(int idOrder){
        return this.orderRepository.existsById(idOrder);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }

    public boolean saveRandomOrder(RamdomOrderDto dto){
        System.out.println(dto.getIdCustomer());
        System.out.println(dto.getOrder_method());

        return this.orderRepository.saveRamdomOrder(dto.getIdCustomer(), dto.getOrder_method());
    }
}

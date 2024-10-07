package com.pizzaio.pizza.web.controller;

import com.pizzaio.pizza.persistence.entity.OrderEntity;
import com.pizzaio.pizza.persistence.pojection.OrderSummary;
import com.pizzaio.pizza.service.OrderService;
import com.pizzaio.pizza.service.dto.RamdomOrderDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    public final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderEntity> getById(@PathVariable int idOrder){
        return ResponseEntity.ok(this.orderService.getById(idOrder));
    }

    @GetMapping("/after/today")
    public ResponseEntity<List<OrderEntity>> getAfterTodayOrders(){
        return ResponseEntity.ok(this.orderService.getAfterTodayOrder());
    }
    @GetMapping("/before/today")
    public ResponseEntity<List<OrderEntity>> getBeforeTodayOrders(){
        return ResponseEntity.ok(this.orderService.getBeforeTodayOrder());
    }
    @PostMapping
    public ResponseEntity<OrderEntity> save(@RequestBody OrderEntity order){
            return ResponseEntity.ok(this.orderService.save(order));
    }
    @PutMapping
    public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity order){
        if(this.orderService.isOrder(order.getIdOrder())){
            return ResponseEntity.ok(this.orderService.save(order));
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
    @GetMapping("/orderSummary/{id}")
    public ResponseEntity<OrderSummary> getCustomerOrderSummary(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getSummary(id));
    }
    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RamdomOrderDto dto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}

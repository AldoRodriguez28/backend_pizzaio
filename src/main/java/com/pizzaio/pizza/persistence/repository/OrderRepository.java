package com.pizzaio.pizza.persistence.repository;

import com.pizzaio.pizza.persistence.entity.OrderEntity;
import com.pizzaio.pizza.persistence.pojection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByDateBefore(LocalDateTime date);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id",nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    @Query(value = "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, po.total AS orderTotal, STRING_AGG(pi.name, ', ') AS pizzaNames\n" +
            "FROM pizza_order po\n" +
            "INNER JOIN customer cu ON po.id_customer = cu.id_customer\n" +
            "INNER JOIN order_item oi ON po.id_order = oi.id_order\n" +
            "INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza\n" +
            "WHERE po.id_order = :orderId\n" +
            "GROUP BY po.id_order, cu.name, po.date, po.total;",nativeQuery = true)
    OrderSummary findSummary(int orderId);

    @Procedure(value = "take_random_pizza_order2", outputParameterName = "order_taken")
    boolean saveRamdomOrder(@Param("idCustomer") String idCustomer,@Param("order_method") String order_method);

}

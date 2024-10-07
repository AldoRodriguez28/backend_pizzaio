package com.pizzaio.pizza.persistence.repository;


import com.pizzaio.pizza.persistence.entity.PizzaEntity;
import com.pizzaio.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;
import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String ingredient);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();

    @Query(value =
            "UPDATE pizza " +
                    "SET price= :newPrice" +
                    "WHERE id_pizza = :idPizza",nativeQuery = true)
    void updatePrice(@Param("idPizza") int idPizza,@Param("newPrice") double newPrice);

    @Query(value =
            "UPDATE pizza " +
                    "SET price= :newPrice " +
                    "WHERE id_pizza = :pizzaId",nativeQuery = true)
    @Modifying
    void updatePriceNewWay(@Param("pizzaId") int pizzaId,@Param("newPrice") double newPrice);
}

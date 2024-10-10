package com.pizzaio.pizza.persistence.repository;

import com.pizzaio.pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}

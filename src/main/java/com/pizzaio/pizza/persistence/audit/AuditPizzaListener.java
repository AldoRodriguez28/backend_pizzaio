package com.pizzaio.pizza.persistence.audit;

import com.pizzaio.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.*;
import org.apache.logging.log4j.util.internal.SerializationUtil;
import org.springframework.util.SerializationUtils;

@MappedSuperclass
public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("POSTLOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE:" + this.currentValue);
        System.out.println("NEW VALUE:" +  entity.toString());
    }

    @PreRemove
    public void oonPreDelete(PizzaEntity entity){
        System.out.println(entity.toString());
    }


}

package com.pizzaio.pizza.web.controller;

import com.pizzaio.pizza.persistence.entity.PizzaEntity;
import com.pizzaio.pizza.service.PizzaService;
import com.pizzaio.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }
    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int elements,
                                                             @RequestParam(defaultValue = "price") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy,sortDirection));
    }
    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int idPizza){
        if(this.pizzaService.isPizza(idPizza)){
            return ResponseEntity.ok(this.pizzaService.get(idPizza));
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() == null || !this.pizzaService.isPizza(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable  int idPizza){
        if(this.pizzaService.isPizza(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/orderByPrice")
    public ResponseEntity<List<PizzaEntity>> getByAvailableOrderByPrice(){
       return ResponseEntity.ok(this.pizzaService.getByAvailableOrderByPrice());

    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<PizzaEntity> getByAvailableOrderByPrice(@PathVariable String name){
        PizzaEntity pizza =this.pizzaService.getPizzaByName(name);
        if(pizza != null){
            return ResponseEntity.ok(this.pizzaService.getPizzaByName(name));
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByPizzaByWith(@PathVariable String ingredient){
        if(this.pizzaService.getWith(ingredient).isEmpty()) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }
    @GetMapping("/withNot/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByPizzaByWithNot(@PathVariable String ingredient){
        if(this.pizzaService.getWithNot(ingredient).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.pizzaService.getWithNot(ingredient));
    }
    @GetMapping("/cheaper/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheaper(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheaper(price));
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && pizzaService.isPizza(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/updatePrice")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if(this.pizzaService.isPizza(dto.getPizzaId())){
            this.pizzaService.updatePriceNewWay(dto.getPizzaId(), dto.getNewPrice());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}

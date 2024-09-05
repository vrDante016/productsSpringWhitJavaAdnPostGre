package com.ProdutosQuitanda.Produtos.controller;

import com.ProdutosQuitanda.Produtos.model.Products;
import com.ProdutosQuitanda.Produtos.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Products> findAll(){
        return productsService.findALl();
    }

    @GetMapping(value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Products> findById(@PathVariable(value = "id") Long id){

        return productsService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Products create(@RequestBody Products products){

        return productsService.insert(products);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
               produces = MediaType.APPLICATION_JSON_VALUE)
    public Products update(@PathVariable(value = "id") Long id,@RequestBody Products products){
        return productsService.update(id,products);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        productsService.deleteById(id);
    }

}

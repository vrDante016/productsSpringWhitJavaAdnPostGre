package com.ProdutosQuitanda.Produtos.service;

import com.ProdutosQuitanda.Produtos.model.ExceptionHandler.CustomizeResponseEntityExceptionHandler;
import com.ProdutosQuitanda.Produtos.model.Exceptions.ResourceNotFoundException;
import com.ProdutosQuitanda.Produtos.model.Products;
import com.ProdutosQuitanda.Produtos.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> findALl(){
        return productsRepository.findAll();
    }

    public Optional<Products> findById(Long id){
        return productsRepository.findById(id);
    }

    public Products insert(Products products){
        if(products == null){
            throw new ResourceNotFoundException("User not found");
        }
        return productsRepository.save(products);
    }

    public void deleteById(Long id){

        productsRepository.deleteById(id);
    }


    public Products update(Long id,Products products){
        Products entity = productsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usario não encontrado"));
         updateUserConfirm(entity, products);
        return productsRepository.save(entity);
    }

    public void updateUserConfirm(Products entity, Products products){
        entity.setName(products.getName());
        entity.setQuantity(products.getQuantity());
        entity.setPrice(products.getPrice());
        entity.setDescription(products.getDescription());

    }


}

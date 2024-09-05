package com.ProdutosQuitanda.Produtos.view.models;

import com.ProdutosQuitanda.Produtos.shared.ProductsDTO;

import java.util.Objects;

public class ProductsRequest {



    private String name;

    private Long quantity;

    private Double price;

    private String description;

    public ProductsRequest(){

    }

    public ProductsRequest(Long id, String name, Long quantity, Double price, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

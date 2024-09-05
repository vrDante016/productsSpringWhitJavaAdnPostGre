package com.ProdutosQuitanda.Produtos.service;

import com.ProdutosQuitanda.Produtos.model.Exceptions.ResourceNotFoundException;
import com.ProdutosQuitanda.Produtos.model.Products;
import com.ProdutosQuitanda.Produtos.repository.ProductsRepository;
import com.ProdutosQuitanda.Produtos.shared.ProductsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductsDTO> findALl(){
        List<Products> products = productsRepository.findAll();
        return products.stream().map(products1 -> new ModelMapper().map(products, ProductsDTO.class)).collect(Collectors.toList());
    }

    public Optional<ProductsDTO> findById(Long id){
        Optional<Products> products  = productsRepository.findById(id);
        if(products.isEmpty()){
            throw new ResourceNotFoundException("Id not found");
        }
        ProductsDTO dto = new ModelMapper().map(products.get(), ProductsDTO.class);
        return Optional.of(dto);
    }

    public ProductsDTO insert(ProductsDTO productsDto){
        productsDto.setId(null);
        ModelMapper mapper = new ModelMapper();


        Products products = mapper.map(productsDto, Products.class);
        products = productsRepository.save(products);

        productsDto.setId(products.getId());
        return productsDto;

    }
    public ProductsDTO update(Long id,ProductsDTO productsDto){
        productsDto.setId(id);
        ModelMapper modelMapper = new ModelMapper();

        Products products = modelMapper.map(productsDto, Products.class);
        productsRepository.save(products);
        return productsDto;
    }

    public void deleteById(Long id){
        Optional<Products> products = productsRepository.findById(id);
        if(products.isEmpty()){
            throw new ResourceNotFoundException("not possible to delete user");
        }
        productsRepository.deleteById(id);
    }





}

package com.ProdutosQuitanda.Produtos.view.controller;

import com.ProdutosQuitanda.Produtos.model.Exceptions.ResourceNotFoundException;
import com.ProdutosQuitanda.Produtos.service.ProductsService;
import com.ProdutosQuitanda.Produtos.shared.ProductsDTO;
import com.ProdutosQuitanda.Produtos.view.models.ProductsRequest;
import com.ProdutosQuitanda.Produtos.view.models.ProductsResponse;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductsResponse> findAll(){
        List<ProductsDTO> products = productsService.findALl();
        ModelMapper mapper = new ModelMapper();

        List<ProductsResponse>responses = products.stream()
                .map(productsDto -> mapper.map(productsDto, ProductsResponse.class)).collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK).getBody();
    }

    @GetMapping(value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ProductsResponse> findById(@PathVariable(value = "id") Long id){
        try{
            Optional<ProductsDTO> productsDTO = productsService.findById(id);
            ProductsResponse products = new ModelMapper().map(productsDTO.get(), ProductsResponse.class);
            return new ResponseEntity<>(Optional.of(products), HttpStatus.OK).getBody();
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponse> create(@RequestBody ProductsResponse productsRequest){
        ModelMapper mapper = new ModelMapper();


        ProductsDTO productsDTO = mapper.map(productsRequest, ProductsDTO.class);
        productsDTO = productsService.insert(productsDTO);
        return new ResponseEntity<>(mapper.map(productsDTO, ProductsResponse.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
               produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponse> update(@PathVariable(value = "id") Long id, @RequestBody ProductsRequest productsRequest){
        ModelMapper mapper = new ModelMapper();
        ProductsDTO productsDTO = mapper.map(productsRequest, ProductsDTO.class);
        productsDTO = productsService.update(productsDTO.getId(),productsDTO);
        return new ResponseEntity<>(mapper.map(productsDTO, ProductsResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        productsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

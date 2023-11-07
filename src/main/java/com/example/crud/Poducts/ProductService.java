package com.example.crud.Poducts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;
    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getProducts(){
        try {
            return this.productsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción como prefieras
            throw new IllegalStateException("Hubo un error al obtener los productos", e);
        }

    }

    public ResponseEntity<Object> newProduct(Products products) {
        Optional<Products> res = productsRepository.findByName(products.getName());
        //utilizo hasmap para mandar mensajes al usuario
        HashMap<String, Object> datos = new HashMap<>();

        if (res.isPresent()){
            datos.put("error",true);
            datos.put("mensaje","ya hay un registro con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("mensaje","se guardo correctamente el producto");
        if (products.getId()>0)
            datos.put("mensaje","se actualizo correctamente el producto");
        productsRepository.save(products);
        datos.put("data",products);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deletedProduct(Long id){
        HashMap<String,Object> datos2 = new HashMap<>();
        boolean existe=this.productsRepository.existsById(id);
        if(!existe){
            datos2.put("error",true);
            datos2.put("mensaje","no existe un producto con ese id");
            return new ResponseEntity<>(
              datos2,
              HttpStatus.CONFLICT
            );
            }
        this.productsRepository.deleteById(id);
        datos2.put("mensaje","el registro se ha eliminado corrctamente");
            return new ResponseEntity<>(
                    datos2,HttpStatus.ACCEPTED
            );
}


}

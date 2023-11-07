package com.example.crud.Poducts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductControler {

private final ProductService productService;


    //contecto producservice o mejor llamado modelo al controlador
    @Autowired
    public ProductControler(ProductService productService) {
        this.productService = productService;
       }

    @GetMapping
    public List<Products> getProducts(){
        return this.productService.getProducts();
    }


    //aca no podemos recibir un producto, sino que tenemos que decirle a spring que el json que esta por venir
    //que tome el body y lo transforme en producto
    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody Products products){
        return this.productService.newProduct(products);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarProducto(@RequestBody Products products){

        return this.productService.newProduct(products);
    }


    @DeleteMapping("{productID}")
    public ResponseEntity<Object> deletedProduct(@PathVariable Long productID){
        return this.productService.deletedProduct(productID);
    }


}

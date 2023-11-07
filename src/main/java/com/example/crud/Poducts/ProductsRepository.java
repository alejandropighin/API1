package com.example.crud.Poducts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {

 Optional<Products> findByName(String name);



}

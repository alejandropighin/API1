package com.example.crud.Poducts;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
    private int  price;
    private LocalDate fecha;


    //indico a antiguedad como un campo que no quiero crear , sino que lo voi a calcular por ende transient realiza eso
    @Transient
    private int antiguedad;

    public int getAntiguedad() {
        return Period.between(this.fecha,LocalDate.now()).getYears();
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}

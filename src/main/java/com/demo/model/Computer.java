package com.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "computers")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String manufacturer;
    private String price;
    private String img;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

}

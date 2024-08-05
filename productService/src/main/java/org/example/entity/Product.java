package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.ProductType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account")
    private String account;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "productType")
    private ProductType productType;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client clientId;
}
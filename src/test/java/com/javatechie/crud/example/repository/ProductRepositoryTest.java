package com.javatechie.crud.example.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; // Not Given By AI
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.javatechie.crud.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private TypedQuery<Product> query;

    @Test
    void testFindByName() {
        String name = "test";
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        Product mockProduct = new Product();
        mockProduct.setId(1);
        mockProduct.setName("product1");
        mockProduct.setPrice(22);
        mockProduct.setQuantity(20);

        when(entityManager.createQuery(jpql, Product.class)).thenReturn(query);
        when(query.setParameter("name", name)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(mockProduct);

        ProductRepository productRepository = new ProductRepository();
        productRepository.entityManager = entityManager;

        Product result = productRepository.findByName(name);

        verify(entityManager, times(1)).createQuery(jpql, Product.class);
        verify(query, times(1)).setParameter("name", name);
        verify(query, times(1)).getSingleResult();

        assertEquals(result.getName(), "product1");
        assertEquals(result.getId(), 1);
        assertEquals(result.getPrice(), 22);
        assertEquals(result.getQuantity(), 20);
    }
}
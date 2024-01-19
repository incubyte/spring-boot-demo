package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Product;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  @PersistenceContext
  public EntityManager entityManager;
public Product findByName(String name) {
  String sql = "SELECT p FROM Product p WHERE p.name = ?";
  PreparedStatement statement = connection.prepareStatement(sql);
  statement.setString(1, name);
  return statement.executeQuery();
}
  }

  @Transactional
  public Product save(Product product) {
    entityManager.persist(product);
    return product;
  }

  @Transactional
  public void deleteById(int id) {
    Product product = entityManager.find(Product.class, id);
    if (product != null) {
      entityManager.remove(product);
    }
  }

  @Transactional
  public List<Product> saveProducts(List<Product> products) {
    for (Product product : products) {
      entityManager.persist(product);
    }
    return products;
  }

  @Transactional
  public Optional<Product> findById(int id) {
    Product product = entityManager.find(Product.class, id);
    return Optional.ofNullable(product);
  }

  @Transactional
  public List<Product> saveAll(List<Product> products) {
    for (Product product : products) {
      entityManager.persist(product);
    }
    return products;
  }

  @Transactional
  public List<Product> findAll() {
    String jpql = "SELECT p FROM Product p";
    return entityManager.createQuery(jpql, Product.class)
        .getResultList();
  }
}

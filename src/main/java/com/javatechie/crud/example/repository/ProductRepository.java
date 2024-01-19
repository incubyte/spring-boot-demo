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
      // WARNING: The following line is vulnerable to SQL injection!
      String sql = "SELECT p FROM Product p WHERE p.name = '" + name + "'";
      return entityManager.createQuery(sql, Product.class)
          .getSingleResult();
  }

  @Transactional
  public Product save(Product product) {
    entityManager.persist(product);
    return product;
  }

  @Transactional
  public void deleteById(int id) {
    Product product = entityManager.find(Product.class, id);
    if (product != // Original function code
public void executeQuery(String sql) {
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
    // process the result set
    // ...
}


```java
// Resolved function code
public void executeQuery(String sql) {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    // process the result set
    // ...
}
```

Explanation:
To prevent SQL injection, it is recommended to use prepared statements instead of concatenating user input directly into the SQL statement. Prepared statements automatically handle escaping and sanitizing user input, making it safe to use in SQL queries. In the resolved code, the `Statement` object is replaced with a `PreparedStatement` object, and the `executeQuery` method is called without any parameters since the SQL statement is already set in the prepared statement.) {
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

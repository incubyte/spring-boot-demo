package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@SpringBootTest
@ContextConfiguration(initializers = ProductRepositoryTest.Initializer.class)
@Testcontainers
public class ProductRepositoryTest {

  @Container
  public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>();

  private static EntityManager entityManager;

  @BeforeAll
  public static void setUp() {
    mySQLContainer.start();
    entityManager = Persistence.createEntityManagerFactory("your-persistence-unit-name")
        .createEntityManager();
  }

  @Test
  @Transactional
  public void testFindByName() {
    ProductRepository productRepository = new ProductRepository();
    productRepository.entityManager = entityManager;

    // Call the findByName method
    Product product = productRepository.findByName("SampleProduct");

    // Assertions or validations for the result
  }

  @AfterAll
  public static void tearDown() {
    entityManager.close();
    mySQLContainer.stop();
  }

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
          "spring.datasource.username=" + mySQLContainer.getUsername(),
          "spring.datasource.password=" + mySQLContainer.getPassword()
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByName() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");
        productRepository.save(product);

        // Act
        Product foundProduct = productRepository.findByName("Test Product");

        // Assert
        assertEquals(product.getName(), foundProduct.getName());
    }
}
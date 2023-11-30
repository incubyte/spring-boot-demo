import com.javatechie.crud.example.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductRepositoryTest {

    @MockBean
    private EntityManager entityManager;

    @Test
    public void testFindByName() {
        // Arrange
        String name = "Test Product";
        Product expectedProduct = new Product();
        expectedProduct.setName(name);

        Query query = mock(Query.class);
        when(entityManager.createQuery(anyString(), eq(Product.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedProduct);

        ProductRepository productRepository = new ProductRepository();
        productRepository.entityManager = entityManager;

        // Act
        Product actualProduct = productRepository.findByName(name);

        // Assert
        verify(entityManager, times(1)).createQuery(anyString(), eq(Product.class));
        verify(query, times(1)).getSingleResult();
        assertEquals(expectedProduct, actualProduct);
    }

    // Add more test methods for other methods in the ProductRepository class
}
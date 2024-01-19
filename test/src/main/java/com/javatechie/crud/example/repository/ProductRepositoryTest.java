import com.javatechie.crud.example.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductRepositoryTest {

    @MockBean
    private EntityManager entityManager;

    @Test
    public void testFindByName() {
        // Arrange
        String name = "TestProduct";
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        TypedQuery<Product> query = mock(TypedQuery.class);
        Product expectedProduct = new Product();
        when(entityManager.createQuery(jpql, Product.class)).thenReturn(query);
        when(query.setParameter("name", name)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedProduct);

        ProductRepository productRepository = new ProductRepository();
        productRepository.entityManager = entityManager;

        // Act
        Product actualProduct = productRepository.findByName(name);

        // Assert
        assertEquals(expectedProduct, actualProduct);
        verify(entityManager, times(1)).createQuery(jpql, Product.class);
        verify(query, times(1)).setParameter("name", name);
        verify(query, times(1)).getSingleResult();
    }
}
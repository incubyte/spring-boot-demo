import com.javatechie.crud.example.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTest {

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private TypedQuery<Product> query;

    @Test
    public void testFindByName() {
        String name = "test";
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";

        when(entityManager.createQuery(jpql, Product.class)).thenReturn(query);
        when(query.setParameter("name", name)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(new Product());

        ProductRepository productRepository = new ProductRepository();
        productRepository.entityManager = entityManager;

        Product result = productRepository.findByName(name);

        verify(entityManager, times(1)).createQuery(jpql, Product.class);
        verify(query, times(1)).setParameter("name", name);
        verify(query, times(1)).getSingleResult();
    }

    // Add more tests for other methods in the ProductRepository class

}
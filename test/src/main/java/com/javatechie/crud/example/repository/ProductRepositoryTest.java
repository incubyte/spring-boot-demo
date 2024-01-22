import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testFindByName() {
        // Arrange
        String name = "test";
        Product expectedProduct = new Product();

        // Act
        Product actualProduct = findProductByName(name);

        // Assert
        assertEquals(expectedProduct, actualProduct);
    }

    private Product findProductByName(String name) {
        // WARNING: The following line is vulnerable to SQL injection!
        String sql = "SELECT p FROM Product p WHERE p.name = '" + name + "'";
        return entityManager.createQuery(sql, Product.class)
            .getSingleResult();
    }
}
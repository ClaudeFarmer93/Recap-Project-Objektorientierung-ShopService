import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById_existingProduct_returnsProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Product actual = repo.getProductById("1")
                .orElseThrow(() -> new ProductNotFoundException("1"));

        //THEN
        Product expected = new Product("1", "Apfel");
        assertEquals(actual, expected);
    }

    @Test
    void getProductById_notExistingProduct_throwsProductNotFoundException() {
        ProductRepo repo = new ProductRepo();

        assertThrows(ProductNotFoundException.class, () -> repo.getProductById("2501")
        .orElseThrow(() -> new ProductNotFoundException("2501")));


    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Product expected = new Product("2", "Banane");
        assertEquals(actual, expected);
        assertEquals(repo.getProductById("2").orElseThrow(), expected);
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");

        //THEN
        assertTrue(repo.getProductById("1").isEmpty());
    }
}

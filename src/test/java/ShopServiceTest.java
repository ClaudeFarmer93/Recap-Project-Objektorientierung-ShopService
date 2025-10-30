import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    private static final Instant fixedTimeForTest = Instant.parse("2025-10-30T11:43:00Z");
    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING,  fixedTimeForTest);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectProductNotFoundException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        //Order actual = shopService.addOrder(productsIds);

        //THEN
        assertThrows(ProductNotFoundException.class, () -> shopService.addOrder(productsIds));
    }
}

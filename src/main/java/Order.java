

import lombok.With;


import java.time.Instant;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        @With OrderStatus status
        //@With Instant orderTime
        ) {
}

/*
@Data
@AllArgsConstructor
public class Order {
    private String id;
    private List<Product> products;
    @With
    private OrderStatus status;

}
*/
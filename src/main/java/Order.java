

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.List;

public record Order(
        String id,
        List<Product> products,
       @With OrderStatus status
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
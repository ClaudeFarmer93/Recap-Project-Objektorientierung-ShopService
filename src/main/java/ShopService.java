import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));


            /*
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }

             */
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status){
       return orderRepo.getOrders().stream()
                .filter(order -> order.status() == status)
                .toList();

    }

    public Order updateOrder(String orderId, OrderStatus newStatus) throws OrderNotFoundException {
        Order currentOrder = orderRepo.getOrderById(orderId);
        if (currentOrder == null) {
            throw new OrderNotFoundException(orderId);
        }
        Order updatedOrder = new Order(currentOrder.withStatus(newStatus));
        orderRepo.removeOrder(currentOrder.id());
        orderRepo.addOrder(updatedOrder);
        return updatedOrder;
    }
}

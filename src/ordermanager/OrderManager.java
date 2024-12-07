package ordermanager;

import model.Order;
import model.Product;

import java.util.List;

public class OrderManager {

    // Thêm đơn hàng
    public void addOrder(List<Order> orders, Order order) {
        orders.add(order);
        System.out.println("Đơn hàng đã được thêm thành công.");
    }

    // Sửa đơn hàng
    public boolean editOrder(List<Order> orders, String orderIdToEdit, Product newProduct) {
        Order orderToEdit = findOrderById(orders, orderIdToEdit);
        if (orderToEdit != null) {
            orderToEdit.setProduct(newProduct);
            System.out.println("Đơn hàng đã được cập nhật.");
            return true;
        } else {
            System.out.println("Không tìm thấy đơn hàng với ID: " + orderIdToEdit);
            return false;
        }
    }

    // Xóa đơn hàng
    public boolean deleteOrder(List<Order> orders, String orderIdToDelete) {
        Order orderToDelete = findOrderById(orders, orderIdToDelete);
        if (orderToDelete != null) {
            orders.remove(orderToDelete);
            System.out.println("Đơn hàng đã được xóa.");
            return true;
        } else {
            System.out.println("Không tìm thấy đơn hàng với ID: " + orderIdToDelete);
            return false;
        }
    }

    // Tìm kiếm đơn hàng theo ID
    private Order findOrderById(List<Order> orders, String orderId) {
        for (Order order : orders) {
            if (order.getProduct().getProductId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}


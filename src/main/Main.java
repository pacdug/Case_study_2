package main;

import model.*;
import ordermanager.OrderManager;
import service.*;
import exception.ValidationException;
import factory.UserFactory;
import util.FileUtil;
import java.io.IOException;
import java.util.*;
import static util.FileUtil.generateOrderDetailsForCSV;

public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng Scanner để đọc dữ liệu từ bàn phím
        Scanner scanner = new Scanner(System.in);

        // Danh sách đơn hàng
        List<Order> orders = new ArrayList<>();

        // Tạo đối tượng OrderManager
        OrderManager orderManager = new OrderManager();

        try {
            // Tạo đối tượng User
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            User user = UserFactory.createUser(username, email);

            // Kiểm tra tính hợp lệ của User
            UserService userService = new UserService();
            userService.validateUser(user);

            // Menu chính
            while (true) {
                System.out.println("\n1. Thêm đơn hàng");
                System.out.println("2. Sửa đơn hàng");
                System.out.println("3. Xóa đơn hàng");
                System.out.println("4. Xem danh sách đơn hàng");
                System.out.println("5. Lưu đơn hàng vào file CSV");
                System.out.println("6. Thoát");
                System.out.print("Chọn lựa chọn: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Đọc ký tự newline

                switch (choice) {
                    case 1:
                        // Thêm đơn hàng
                        System.out.print("Enter product ID: ");
                        String productId = scanner.nextLine();

                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine();

                        System.out.print("Enter product price: ");
                        double productPrice = scanner.nextDouble();
                        scanner.nextLine();  // Đọc ký tự newline

                        Product product = new Product(productId, productName, productPrice);
                        Order order = new Order(user, product);
                        orderManager.addOrder(orders, order);
                        break;

                    case 2:
                        // Sửa đơn hàng
                        System.out.print("Enter Order ID to edit: ");
                        String orderIdToEdit = scanner.nextLine();

                        System.out.print("Enter new product ID: ");
                        String newProductId = scanner.nextLine();

                        System.out.print("Enter new product name: ");
                        String newProductName = scanner.nextLine();

                        System.out.print("Enter new product price: ");
                        double newProductPrice = scanner.nextDouble();
                        scanner.nextLine();  // Đọc ký tự newline

                        Product newProduct = new Product(newProductId, newProductName, newProductPrice);
                        if (orderManager.editOrder(orders, orderIdToEdit, newProduct)) {
                            FileUtil.writeCSVFile("orders.csv", generateOrderDetailsForCSV(orders));
                        }
                        break;

                    case 3:
                        // Xóa đơn hàng
                        System.out.print("Enter Order ID to delete: ");
                        String orderIdToDelete = scanner.nextLine();
                        if (orderManager.deleteOrder(orders, orderIdToDelete)) {
                            FileUtil.writeCSVFile("orders.csv", generateOrderDetailsForCSV(orders));
                        }
                        break;

                    case 4:
                        // Hiển thị danh sách đơn hàng
                        if (orders.isEmpty()) {
                            System.out.println("Danh sách đơn hàng trống.");
                        } else {
                            System.out.println("\nDanh sách đơn hàng:");
                            for (Order ord : orders) {
                                System.out.println("Order ID: " + ord.getProduct().getProductId() +
                                        ", User: " + ord.getUser().getUsername() +
                                        ", Product: " + ord.getProduct().getName() +
                                        ", Price: " + ord.getProduct().getPrice());
                            }
                        }
                        break;

                    case 5:
                        // Lưu đơn hàng vào file CSV
                        FileUtil.writeCSVFile("orders.csv", generateOrderDetailsForCSV(orders));
                        FileUtil.writeBinaryFile("users.dat", user);
                        System.out.println("Đơn hàng và thông tin người dùng đã được lưu.");
                        break;

                    case 6:
                        // Thoát
                        System.out.println("Thoát chương trình.");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            }
        } catch (ValidationException | IOException e) {
            e.printStackTrace();
        } finally {
            // Đóng Scanner để giải phóng tài nguyên
            scanner.close();
        }
    }
}

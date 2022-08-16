package ordermanagement.controller;

import ordermanagement.Entity.LicenseStatus;
import ordermanagement.Entity.Order;
import ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> listAll() {
        return orderService.listAllOrders();
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable long orderId) {
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/licenses/{licenseId}")
    public List<LicenseStatus> getLicenseById(@PathVariable long licenseId) {
        return orderService.getLicenseById(licenseId);
    }

    @PutMapping("/license_keys/{licenseKey}")
    public LicenseStatus updateStatus(@PathVariable String licenseKey) {
        return orderService.updateStatus(licenseKey);
    }

}

package ordermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ordermanagement.Entity.LicenseStatus;
import ordermanagement.Entity.Order;
import ordermanagement.controller.OrderController;
import ordermanagement.service.OrderService;

@SpringBootTest(classes = {OrderControllerMockitoTest.class})
public class OrderControllerMockitoTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;


    @Test
    public void createOrderTest() {
        Order order = new Order(0,10);
        when(orderService.createOrder(order)).thenReturn(order);
        assertEquals(order, orderController.createOrder(order));
    }

    @Test
    public void getOrderByIdTest() {
        long orderId = 5L;
        Order order = new Order(1,10);
        order.setOrderId(5);
        when(orderService.getOrderById(orderId)).thenReturn(order);
        Order res = orderController.getOrderById(orderId);

        assertEquals(orderId, res.getOrderId());
    }

    @Test
    public void getLicenseByIdTest() {
        long licenseId = 1L;
        List<LicenseStatus> licenseStatus = new ArrayList<>();

        licenseStatus.add(new LicenseStatus(licenseId));
        when(orderService.getLicenseById(licenseId)).thenReturn(licenseStatus);
        assertEquals(1, orderController.getLicenseById(licenseId).size());
    }

    @Test
    public void deleteOrderTest() {
        long orderId = 1L;
        orderController.deleteOrder(orderId);
        verify(orderService, times(1)).deleteOrder(orderId);
    }

    @Test
    public void listAllOrdersTest() {
        List<Order> availableOrders = new ArrayList<Order>();
        availableOrders.add(new Order(20, 20));
        availableOrders.add(new Order(122, 20));

        when(orderService.listAllOrders()).thenReturn(availableOrders);
        assertEquals(2, orderController.listAll().size());
    }

    @Test
    public void updateStatusTest() {
        LicenseStatus licenseStatus = new LicenseStatus();
        String licenseKey = "bac0f5c0-4b76-4581-a3fe-84a8bdbbbf52";
        when(orderService.updateStatus(licenseKey)).thenReturn(licenseStatus);
        assertEquals(licenseStatus, orderController.updateStatus(licenseKey));
    }


}

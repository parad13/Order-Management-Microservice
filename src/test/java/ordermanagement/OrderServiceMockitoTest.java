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
import ordermanagement.repository.LicenseStatusRepository;
import ordermanagement.repository.OrderRepository;
import ordermanagement.service.OrderService;

@SpringBootTest(classes = { OrderServiceMockitoTest.class })
public class OrderServiceMockitoTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    LicenseStatusRepository licenseStatusRepository;

    @InjectMocks
    OrderService orderService;

    public List<Order> availableOrders;

    @Test
    public void createOrderTest() {
        Order order = new Order(0,10);
        when(orderRepository.save(order)).thenReturn(order);
        assertEquals(order, orderService.createOrder(order));
    }


    @Test
    public void getOrderByIdTest() {
        long orderId = 5L;
        Order order = new Order(1,10);
        order.setOrderId(5);
        when(orderRepository.findByOrderId(orderId)).thenReturn(order);
        Order res = orderService.getOrderById(orderId);

        assertEquals(orderId, res.getOrderId());
    }

    @Test
    public void getLicenseByIdTest() {
        long licenseId = 1L;
        List<LicenseStatus> licenseStatus = new ArrayList<>();

        licenseStatus.add(new LicenseStatus(licenseId));
        when(licenseStatusRepository.findByLicenseId(licenseId)).thenReturn(licenseStatus);
        assertEquals(1, orderService.getLicenseById(licenseId).size());
    }

    @Test
    public void deleteOrderTest() {
        long orderId = 1;
        orderService.deleteOrder(orderId);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    public void listAllOrdersTest() {
        List<Order> availableOrders = new ArrayList<Order>();
        availableOrders.add(new Order(20, 20));
        availableOrders.add(new Order(122, 20));

        when(orderRepository.findAll()).thenReturn(availableOrders);
        assertEquals(2, orderService.listAllOrders().size());
    }

//	@Test
//	public void updateStatusTest() {
//		LicenseStatus licenseStatus = new LicenseStatus(111);
//		String licenseKey = "f88e0637-da2f-4d58-a329-dffbaf40f520";
//		when(licenseStatusRepository.findByLicenseKey(licenseKey)).thenReturn(licenseStatus);
//		assertEquals(licenseStatus, orderService.updateStatus(licenseKey));
//	}

}

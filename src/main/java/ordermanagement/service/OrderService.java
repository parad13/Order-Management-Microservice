package ordermanagement.service;

import ordermanagement.Entity.LicenseStatus;
import ordermanagement.Entity.Order;
import ordermanagement.repository.LicenseStatusRepository;
import ordermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private LicenseStatusRepository licenseStatusRepository;

	public Order createOrder(Order order) {
		Order create = orderRepository.save(order);
		int quantity = order.getQuantity();
		while (quantity-- > 0) {
			LicenseStatus licenseStatus = new LicenseStatus(order.getLicenseId());
			licenseStatus.setOrderId(create.getOrderId());
			licenseStatusRepository.save(licenseStatus);
		}
		return create;
	}

	public List<Order> listAllOrders() {
		return orderRepository.findAll();
	}

	public void deleteOrder(long orderId) {

		List<LicenseStatus> licenseList = licenseStatusRepository.findByOrderId(orderId);
		orderRepository.deleteById(orderId);
		licenseStatusRepository.deleteAll(licenseList);
	}

	public Order getOrderById(long orderId) {
		return orderRepository.findByOrderId(orderId);

	}

	public List<LicenseStatus> getLicenseById(long licenseId) {
		return licenseStatusRepository.findByLicenseId(licenseId);
	}

	public LicenseStatus updateStatus(String licenseKey) {
		try {

			LicenseStatus licenseStatus = licenseStatusRepository.findByLicenseKey(licenseKey);
			if ((licenseStatus.getLicenseKeyStatus()).equals("NOT CONSUMED")) {
				licenseStatus.setLicenseKeyStatus("CONSUMED");
			} else {
				licenseStatus.setLicenseKeyStatus("NOT CONSUMED");
			}
			return licenseStatusRepository.save(licenseStatus);
		} catch (Exception e) {
			throw new RuntimeException("License Key Not Found");
		}

	}



}
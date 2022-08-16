package ordermanagement.repository;

import java.util.List;

import ordermanagement.Entity.LicenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseStatusRepository extends JpaRepository<LicenseStatus, String> {

    List<LicenseStatus> findByLicenseId(long license_id);

    LicenseStatus findByLicenseKey(String licenseKey);

    List<LicenseStatus> findByOrderId(long orderId);





}

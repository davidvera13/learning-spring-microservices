package io.warehouse13.accounts.io.repository;

import io.warehouse13.accounts.io.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	// derived name method
	Optional<CustomerEntity> findByMobileNumber(String mobileNumber);
}

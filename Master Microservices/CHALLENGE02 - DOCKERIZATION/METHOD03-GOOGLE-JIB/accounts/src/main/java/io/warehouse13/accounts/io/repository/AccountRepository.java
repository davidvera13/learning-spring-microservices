package io.warehouse13.accounts.io.repository;

import io.warehouse13.accounts.io.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	Optional<AccountEntity> findByCustomerId(Long customerId);


	// modifying indicate to spring boot jpa that data will be modified: the query must use a transaction.
	// if transaction fails, there will be a rollback
	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);
}

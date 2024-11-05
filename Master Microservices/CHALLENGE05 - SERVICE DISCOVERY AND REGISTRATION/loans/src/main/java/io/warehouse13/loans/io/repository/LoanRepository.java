package io.warehouse13.loans.io.repository;

import io.warehouse13.loans.io.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    Optional<LoanEntity> findByMobileNumber(String mobileNumber);

    Optional<LoanEntity> findByLoanNumber(String loanNumber);

}

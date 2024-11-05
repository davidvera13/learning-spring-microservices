package io.warehouse13.loans.io.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class LoanEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	private String mobileNumber;

	private String loanNumber;

	private String loanType;

	private int totalLoan;

	private int amountPaid;

	private int outstandingAmount;
	
}

package io.warehouse13.accounts.io.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@ToString
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AccountEntity extends BaseEntity{
	@Id
	@Column(name = "account_number")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "branch_address")
	private String branchAddress;

	// reference to the customer, should be foreign key
	@Column(name = "customer_id")
	private Long customerId;

}

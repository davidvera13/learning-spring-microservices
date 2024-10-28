package io.warehouse13.accounts.io.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@ToString
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CustomerEntity extends BaseEntity{
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "mobileNumber")
	private String mobileNumber;
}

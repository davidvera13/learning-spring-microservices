package io.warehouse13.accounts.mapper;

import io.warehouse13.accounts.dto.CustomerDto;
import io.warehouse13.accounts.io.entity.CustomerEntity;

public class CustomerMapper {
	public static CustomerDto mapToCustomerDto(CustomerEntity customer, CustomerDto customerDto) {
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		return customerDto;
	}

	public static CustomerEntity mapToCustomer(CustomerDto customerDto, CustomerEntity customer) {
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}
}

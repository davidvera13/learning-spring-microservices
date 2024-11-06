package io.warehouse13.accounts.service;


import io.warehouse13.accounts.dto.CustomerDetailsDto;

public interface CustomerService {
	CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}

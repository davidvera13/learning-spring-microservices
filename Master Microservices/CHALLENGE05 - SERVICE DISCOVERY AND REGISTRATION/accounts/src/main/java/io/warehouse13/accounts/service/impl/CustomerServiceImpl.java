package io.warehouse13.accounts.service.impl;

import io.warehouse13.accounts.clients.CardsFeignClient;
import io.warehouse13.accounts.clients.LoansFeignClient;
import io.warehouse13.accounts.clients.dto.CardDto;
import io.warehouse13.accounts.clients.dto.LoanDto;
import io.warehouse13.accounts.dto.AccountDto;
import io.warehouse13.accounts.dto.CustomerDetailsDto;
import io.warehouse13.accounts.dto.CustomerDto;
import io.warehouse13.accounts.exception.ResourceNotFoundException;
import io.warehouse13.accounts.io.entity.AccountEntity;
import io.warehouse13.accounts.io.entity.CustomerEntity;
import io.warehouse13.accounts.io.repository.AccountRepository;
import io.warehouse13.accounts.io.repository.CustomerRepository;
import io.warehouse13.accounts.mapper.AccountMapper;
import io.warehouse13.accounts.mapper.CustomerMapper;
import io.warehouse13.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final AccountRepository accountRepository;
	private final LoansFeignClient loansFeignClient;
	private final CardsFeignClient cardsFeignClient;

	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		// fetching from account-ws databse
		CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		AccountEntity accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
		);
		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
		customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(accounts, new AccountDto()));

		// calling external webservices
		ResponseEntity<LoanDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
		ResponseEntity<CardDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);

		// updating customer details with loand & cards details
		customerDetailsDto.setLoanDto(loansDtoResponseEntity.getBody());
		customerDetailsDto.setCardDto(cardsDtoResponseEntity.getBody());

		return customerDetailsDto;
	}
}

package io.warehouse13.accounts.service.impl;

import io.warehouse13.accounts.dto.AccountDto;
import io.warehouse13.accounts.dto.CustomerDto;
import io.warehouse13.accounts.exception.CustomerAlreadyExistsException;
import io.warehouse13.accounts.exception.ResourceNotFoundException;
import io.warehouse13.accounts.io.entity.AccountEntity;
import io.warehouse13.accounts.io.entity.CustomerEntity;
import io.warehouse13.accounts.io.repository.AccountRepository;
import io.warehouse13.accounts.io.repository.CustomerRepository;
import io.warehouse13.accounts.mapper.AccountMapper;
import io.warehouse13.accounts.mapper.CustomerMapper;
import io.warehouse13.accounts.service.AccountService;
import io.warehouse13.accounts.shared.AccountsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	/**
	 *
	 * @param customerDto - CustomerDto Object
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		CustomerEntity customer = CustomerMapper.mapToCustomer(customerDto, new CustomerEntity());
		Optional<CustomerEntity> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(optionalCustomer.isPresent())
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
					+ customerDto.getMobileNumber());
		// JpaAuditing added
		//customer.setCreatedAt(LocalDateTime.now());
		//customer.setCreatedBy("Anonymous");
		CustomerEntity savedCustomer = customerRepository.save(customer);
		accountRepository.save(createNewAccount(savedCustomer));
	}

	/**
	 * @param mobileNumber - Input Mobile Number
	 * @return Accounts Details based on a given mobileNumber
	 */
	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		AccountEntity accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
		);
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountDto(AccountMapper.mapToAccountDto(accounts, new AccountDto()));
		return customerDto;
	}


	/**
	 * @param customerDto - CustomerDto Object
	 * @return boolean indicating if the update of Account details is successful or not
	 */
	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountDto accountsDto = customerDto.getAccountDto();
		if(accountsDto !=null ){
			AccountEntity account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
			);
			AccountMapper.mapToAccount(accountsDto, account);
			account = accountRepository.save(account);
			Long customerId = account.getCustomerId();
			CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
					() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
			);
			CustomerMapper.mapToCustomer(customerDto,customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		return  isUpdated;
	}

	/**
	 * @param mobileNumber - Input Mobile Number
	 * @return boolean indicating if the delete of Account details is successful or not
	 */
	@Override
	public boolean deleteAccount(String mobileNumber) {
		CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
		);
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

	/**
	 * @param customer - Customer Object
	 * @return the new account details
	 */
	private AccountEntity createNewAccount(CustomerEntity customer) {
		AccountEntity newAccount = new AccountEntity();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		// JpaAuditing added
		//newAccount.setCreatedAt(LocalDateTime.now());
		//newAccount.setCreatedBy("Anonymous");
		return newAccount;
	}
}

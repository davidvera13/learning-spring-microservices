package io.warehouse13.accounts.mapper;

import io.warehouse13.accounts.dto.AccountDto;
import io.warehouse13.accounts.io.entity.AccountEntity;

public class AccountMapper {
	public static AccountDto mapToAccountDto(AccountEntity account, AccountDto accountDto) {
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAccountType(account.getAccountType());
		accountDto.setBranchAddress(account.getBranchAddress());
		return accountDto;
	}

	public static AccountEntity mapToAccount(AccountDto accountDto, AccountEntity account) {
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBranchAddress(accountDto.getBranchAddress());
		return account;
	}
}
